package com.example.jdagnogo.alertlebonsoinappart;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.request.target.ViewTarget;
import com.evernote.android.job.JobManager;
import com.example.jdagnogo.alertlebonsoinappart.services.dagger.DaggerNetworkComponent;
import com.example.jdagnogo.alertlebonsoinappart.services.dagger.NetworkComponent;
import com.example.jdagnogo.alertlebonsoinappart.services.dagger.NetworkModule;
import com.example.jdagnogo.alertlebonsoinappart.services.jobs.DemoJobCreator;
import com.example.jdagnogo.alertlebonsoinappart.utils.Constants;
import com.facebook.stetho.InspectorModulesProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.rhino.JsRuntimeReplFactoryBuilder;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import jonathanfinerty.once.Once;

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
        Once.initialise(this);
        ViewTarget.setTagId(R.id.glide_tag);
        JobManager.create(this).addJobCreator(new DemoJobCreator());

        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constants.SITE_URL))
                .build();

        demoJobCreator = new DemoJobCreator();

        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableWebKitInspector(new InspectorModulesProvider() {
                    @Override
                    public Iterable<ChromeDevtoolsDomain> get() {
                        return new Stetho.DefaultInspectorModulesBuilder(AlertLEboncoinApplication.this).runtimeRepl(
                                new JsRuntimeReplFactoryBuilder(AlertLEboncoinApplication.this)
                                        .importPackage("com.example.jdagnogo.alertlebonsoinappart")
                                        .addVariable("help",com.example.jdagnogo.alertlebonsoinappart.StethoConsole.help())
                                        .build()
                        ).finish();
                    }
                })
                .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                .build());
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
