package com.example.jdagnogo.alertlebonsoinappart;

import android.app.Application;
import android.content.Context;

import com.evernote.android.job.JobManager;
import com.example.jdagnogo.alertlebonsoinappart.services.dagger.DaggerNetworkComponent;
import com.example.jdagnogo.alertlebonsoinappart.services.dagger.NetworkComponent;
import com.example.jdagnogo.alertlebonsoinappart.services.dagger.NetworkModule;
import com.example.jdagnogo.alertlebonsoinappart.services.jobs.DemoJobCreator;
import com.example.jdagnogo.alertlebonsoinappart.utils.Constants;

public class AlertLEboncoinApplication extends Application {
    private NetworkComponent networkComponent;
    private static AlertLEboncoinApplication instance;
    private DemoJobCreator demoJobCreator;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        JobManager.create(this).addJobCreator(new DemoJobCreator());

        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constants.SITE_URL))
                .build();

        demoJobCreator = new DemoJobCreator();
    }

    public static Context getContext() {
        return instance.getBaseContext();
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }

    public static DemoJobCreator getDemoJobCreator() {
        if (instance.getDemoJobCreator() == null) {
            return new DemoJobCreator();
        } else return instance.getDemoJobCreator();
    }
}
