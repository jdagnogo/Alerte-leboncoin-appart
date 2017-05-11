package com.example.jdagnogo.alertlebonsoinappart.services.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NetworkModule {
    private String urlPath;

    public NetworkModule(String urlPath) {
        this.urlPath = urlPath;
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlPath)
                .build();
        return retrofit;
    }
}
