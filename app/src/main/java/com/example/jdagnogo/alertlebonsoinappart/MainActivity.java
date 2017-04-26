package com.example.jdagnogo.alertlebonsoinappart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.jdagnogo.alertlebonsoinappart.enums.City;
import com.example.jdagnogo.alertlebonsoinappart.enums.Meuble;
import com.example.jdagnogo.alertlebonsoinappart.models.RequestItems;
import com.example.jdagnogo.alertlebonsoinappart.services.UrlRequestBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }

    private void test(){
        RequestItems requestItems = new RequestItems();
        List<City> cities = new ArrayList<>();
        cities.add(City.BORDEAUX_ALL);
        requestItems.setCities(cities);
        requestItems.setMeuble(Meuble.MEUBLE);

        String url = UrlRequestBuilder.createUrl(requestItems);
        Log.d(TAG,"URL : "+url);
    }
}
