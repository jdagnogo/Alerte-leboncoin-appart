package com.example.jdagnogo.alertlebonsoinappart.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.jdagnogo.alertlebonsoinappart.AlertLEboncoinApplication;
import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.adapter.ResultResearchAppartAdapter;
import com.example.jdagnogo.alertlebonsoinappart.enums.City;
import com.example.jdagnogo.alertlebonsoinappart.enums.Meuble;
import com.example.jdagnogo.alertlebonsoinappart.models.Appart;
import com.example.jdagnogo.alertlebonsoinappart.models.RequestItems;
import com.example.jdagnogo.alertlebonsoinappart.services.UrlRequestBuilder;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.GlobalBus;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.UpdateAppartsBus;
import com.example.jdagnogo.alertlebonsoinappart.services.jobs.DemoJobCreator;
import com.example.jdagnogo.alertlebonsoinappart.services.jobs.DemoSyncJob;
import com.example.jdagnogo.alertlebonsoinappart.services.retrofit.RetrofitNetworkInterface;

import org.greenrobot.eventbus.Subscribe;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.NEW_RESEARCH;

public class ResultActivity extends AppCompatActivity {
    private final static String TAG = ResultActivity.class.getCanonicalName();
    private final static int MAX_NB_APPART = 10;
    private List<Appart> apparts;
    private RecyclerView recycleListView;
    private ResultResearchAppartAdapter adapter;


    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apparts = new ArrayList<>();
        GlobalBus.getBus().register(this);
        RequestItems requestItems;
        if (getIntent() != null) {
            requestItems = getIntent().getParcelableExtra(NEW_RESEARCH);

            ((AlertLEboncoinApplication) getApplication()).getNetworkComponent().inject(ResultActivity.this);

            getAppart(requestItems.createHashMap());
        }
    }

    private void initRecycler() {
        recycleListView = (RecyclerView) findViewById(R.id.recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycleListView.setLayoutManager(mLayoutManager);
        adapter = new ResultResearchAppartAdapter();
        recycleListView.setAdapter(adapter);
        adapter.setData(apparts);
        adapter.notifyDataSetChanged();
    }

    private void test() {
        final RequestItems requestItems = new RequestItems();
        List<City> cities = new ArrayList<>();
        cities.add(City.BORDEAUX_ALL);
        cities.add(City.TALENCE);
        requestItems.setCities(cities);
        requestItems.setMeuble(Meuble.MEUBLE);

        String url = UrlRequestBuilder.createUrl(requestItems);
        Log.d(TAG, "URL : " + url);

        DemoJobCreator demoJobCreator = AlertLEboncoinApplication.getDemoJobCreator();
        demoJobCreator.create(DemoSyncJob.TAG);
        DemoSyncJob demoSyncJob = new DemoSyncJob();
        demoSyncJob.scheduleJob();


    }

    private void getAppart(final HashMap<String, String> params) {
        RetrofitNetworkInterface mService = retrofit.create(RetrofitNetworkInterface.class);
        Call<ResponseBody> mSong = mService.getApparts(params);
        mSong.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Document document = Jsoup.parse(response.body().string());
                    Elements ensemble = document.getElementsByClass("list_item");
                    List<Appart> apparts = new ArrayList<Appart>();
                    for (int i = 0; i < MAX_NB_APPART; i++) {

                        String title = ensemble.get(i).getElementsByClass("item_infos").get(0).getElementsByClass("item_title").get(0).text();
                        String imageUrl = "";
                        if (0 == ensemble.get(i).getElementsByClass("item_image").get(0).getElementsByClass("lazyload").size()) {
                            continue;
                        }
                        String price = ensemble.get(i).getElementsByClass("item_infos").get(0).getElementsByClass("item_price").get(0).text();
                        if (0 == ensemble.get(i).getElementsByClass("item_infos").get(0).getElementsByClass("item_absolute").size()) {
                            continue;
                        }
                        String date = ensemble.get(i).getElementsByClass("item_infos").get(0).getElementsByClass("item_absolute").get(0).getElementsByClass("item_supp").get(0).text();

                        Appart appart = new Appart(imageUrl, price, title, date, false);
                        apparts.add(appart);
                    }

                    Log.d(TAG, "Result " + response.body().string());
                    UpdateAppartsBus event = new UpdateAppartsBus(apparts);
                    GlobalBus.getBus().post(event);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "Display error code " + t.toString());
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        GlobalBus.getBus().unregister(this);
    }

    @Subscribe
    public void getMessage(UpdateAppartsBus updateSwipeViewBus) {
        apparts = updateSwipeViewBus.getApparts();
        initRecycler();

    }
}
