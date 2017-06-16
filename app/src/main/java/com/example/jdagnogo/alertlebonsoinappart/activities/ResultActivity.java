package com.example.jdagnogo.alertlebonsoinappart.activities;

import android.animation.Animator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.example.jdagnogo.alertlebonsoinappart.models.Search;
import com.example.jdagnogo.alertlebonsoinappart.models.realm.RequestItemsRealm;
import com.example.jdagnogo.alertlebonsoinappart.services.UrlRequestBuilder;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.GlobalBus;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.UpdateAppartsBus;
import com.example.jdagnogo.alertlebonsoinappart.services.jobs.DemoJobCreator;
import com.example.jdagnogo.alertlebonsoinappart.services.jobs.DemoSyncJob;
import com.example.jdagnogo.alertlebonsoinappart.services.retrofit.RetrofitNetworkInterface;
import com.example.jdagnogo.alertlebonsoinappart.utils.Parser;
import com.willowtreeapps.spruce.Spruce;
import com.willowtreeapps.spruce.animation.DefaultAnimations;
import com.willowtreeapps.spruce.sort.DefaultSort;

import org.greenrobot.eventbus.Subscribe;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import io.realm.Realm;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.NEW_RESEARCH;

public class ResultActivity extends AppCompatActivity {
    private final static String TAG = ResultActivity.class.getCanonicalName();

    private List<Appart> apparts;
    private RecyclerView recycleListView;
    private ResultResearchAppartAdapter adapter;
    private Realm realm;
    private HashMap<String, String> map;
    private RequestItemsRealm requestItemsRealm;
    @Bind(R.id.alarm)
    FloatingActionButton alarm;
    @Inject
    Retrofit retrofit;
    DemoSyncJob demoSyncJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        apparts = new ArrayList<>();
        initJob();
        GlobalBus.getBus().register(this);
        RequestItems requestItems;

        if (getIntent() != null) {
            requestItems = getIntent().getParcelableExtra(NEW_RESEARCH);

            ((AlertLEboncoinApplication) getApplication()).getNetworkComponent().inject(ResultActivity.this);
            map = requestItems.createHashMap();

            requestItemsRealm = new RequestItemsRealm(requestItems);
            getAppart(map);


        }
    }

    @OnClick(R.id.alarm)
    public void setOnAlarmClick() {
        Number nextID = (realm.where(Search.class).max("id"));
        demoSyncJob.scheduleJob(nextID.intValue());
    }

    private void initJob() {
        DemoJobCreator demoJobCreator = new DemoJobCreator();
        demoJobCreator.create(DemoSyncJob.TAG);
        demoSyncJob = new DemoSyncJob();
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

    private void getAppart(final HashMap<String, String> params) {
        RetrofitNetworkInterface mService = retrofit.create(RetrofitNetworkInterface.class);
        Call<ResponseBody> mSong = mService.getApparts(params);
        mSong.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    List<Appart> apparts = Parser.parseHtml(response);
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

    private void addSearchToDb(Search search) {
        realm = ((AlertLEboncoinApplication) getApplication()).getRealm();
        realm.beginTransaction();
        Number nextID = (realm.where(Search.class).max("id"));
        search.setId(nextID.intValue() + 1);
        realm.copyToRealm(search);
        realm.commitTransaction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GlobalBus.getBus().unregister(this);
        if (realm != null) {
            realm.close();
            realm = null;
        }
    }

    @Subscribe
    public void getMessage(UpdateAppartsBus updateSwipeViewBus) {
        apparts = updateSwipeViewBus.getApparts();
        initRecycler();
        Animator spruceAnimator = new Spruce
                .SpruceBuilder(recycleListView)
                .sortWith(new DefaultSort(/*interObjectDelay=*/50L))
                .animateWith(new Animator[] {DefaultAnimations.shrinkAnimator(recycleListView, /*duration=*/800)})
                .start();
        Search search = new Search("toto", new Date(), apparts.get(0), requestItemsRealm);
        addSearchToDb(search);

    }
}
