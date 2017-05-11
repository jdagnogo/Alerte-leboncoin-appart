package com.example.jdagnogo.alertlebonsoinappart.services.dagger;

import com.example.jdagnogo.alertlebonsoinappart.activities.MainActivity;

import javax.inject.Singleton;
import dagger.Component;
@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    public void inject(MainActivity activity);
}