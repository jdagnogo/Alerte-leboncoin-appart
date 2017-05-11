package com.example.jdagnogo.alertlebonsoinappart.services.dagger;

import com.example.jdagnogo.alertlebonsoinappart.activities.MainActivity;
import com.example.jdagnogo.alertlebonsoinappart.services.jobs.DemoSyncJob;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    public void inject(MainActivity activity);
    public void inject(DemoSyncJob jobCreator);
}