package com.example.jdagnogo.alertlebonsoinappart.services.dagger;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
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
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlPath)
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
