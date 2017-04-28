package com.example.jdagnogo.alertlebonsoinappart;

import android.app.Application;
import android.content.Context;

import com.evernote.android.job.JobManager;
import com.example.jdagnogo.alertlebonsoinappart.services.jobs.DemoJobCreator;

public class AlertLEboncoinApplication extends Application {
private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        JobManager.create(this).addJobCreator(new DemoJobCreator());
    }
    public static Context getContext(){
        return context;
    }
}
