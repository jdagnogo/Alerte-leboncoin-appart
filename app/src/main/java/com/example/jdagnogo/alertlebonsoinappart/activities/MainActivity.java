package com.example.jdagnogo.alertlebonsoinappart.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.jdagnogo.alertlebonsoinappart.AlertLEboncoinApplication;
import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.enums.City;
import com.example.jdagnogo.alertlebonsoinappart.enums.Meuble;
import com.example.jdagnogo.alertlebonsoinappart.models.RequestItems;
import com.example.jdagnogo.alertlebonsoinappart.services.RetrofitNetworkInterface;
import com.example.jdagnogo.alertlebonsoinappart.services.UrlRequestBuilder;
import com.example.jdagnogo.alertlebonsoinappart.services.jobs.DemoJobCreator;
import com.example.jdagnogo.alertlebonsoinappart.services.jobs.DemoSyncJob;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getCanonicalName();
    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }

    private void test() {
        RequestItems requestItems = new RequestItems();
        List<City> cities = new ArrayList<>();
        cities.add(City.BORDEAUX_ALL);
        cities.add(City.TALENCE);
        requestItems.setCities(cities);
        requestItems.setMeuble(Meuble.MEUBLE);



        String url = UrlRequestBuilder.createUrl(requestItems);
        Log.d(TAG, "URL : " + url);

        DemoJobCreator demoJobCreator = new DemoJobCreator();
        demoJobCreator.create(DemoSyncJob.TAG);
        DemoSyncJob demoSyncJob = new DemoSyncJob();
        demoSyncJob.scheduleJob();

        ((AlertLEboncoinApplication)getApplication()).getNetworkComponent().inject(MainActivity.this);
        RetrofitNetworkInterface mService = retrofit.create(RetrofitNetworkInterface.class);
        Call<String> mSong = mService.allappart();
        mSong.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "Result " + response.body());

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "Display error code " + t.toString());
            }
        });

    }
}
