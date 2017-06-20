package com.example.jdagnogo.alertlebonsoinappart.services.dagger;

import com.example.jdagnogo.alertlebonsoinappart.activities.ResultActivity;
import com.example.jdagnogo.alertlebonsoinappart.services.jobs.GetLastAppartJob;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    public void inject(ResultActivity activity);
    public void inject(GetLastAppartJob jobCreator);
}