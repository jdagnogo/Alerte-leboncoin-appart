package com.example.jdagnogo.alertlebonsoinappart;

import android.app.Application;
import android.content.Context;

import com.evernote.android.job.JobManager;

import com.example.jdagnogo.alertlebonsoinappart.services.dagger.DaggerNetworkComponent;
import com.example.jdagnogo.alertlebonsoinappart.services.dagger.NetworkComponent;
import com.example.jdagnogo.alertlebonsoinappart.services.dagger.NetworkModule;
import com.example.jdagnogo.alertlebonsoinappart.services.jobs.DemoJobCreator;
import com.example.jdagnogo.alertlebonsoinappart.utils.Constants;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AlertLEboncoinApplication extends Application {
    private static Context context;
    private NetworkComponent networkComponent;
    private DemoJobCreator demoJobCreator;
    private static AlertLEboncoinApplication instance;
    private Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = this;

        JobManager.create(this).addJobCreator(new DemoJobCreator());

        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constants.SITE_URL))
                .build();

        demoJobCreator = new DemoJobCreator();
    }

    public static Context getContext() {
        return context;
    }

    public Realm getRealm() {
        if (realm != null) {
            if (!realm.isClosed()) {
                return realm;
            }
        }
        realm.init(context);
        return Realm.getInstance(getRealmConfig());
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }

    public static DemoJobCreator getDemoJobCreator() {
        if (instance.getDemoJobCreator() == null) {
            return new DemoJobCreator();
        } else return instance.getDemoJobCreator();
    }

    public static RealmConfiguration getRealmConfig() {
        return new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
    }
}
