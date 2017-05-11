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
private static Context context;
    private NetworkComponent networkComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        JobManager.create(this).addJobCreator(new DemoJobCreator());

        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constants.SITE_URL))
                .build();
    }
    public static Context getContext(){
        return context;
    }

    public NetworkComponent getNetworkComponent(){
        return networkComponent;
    }
}
