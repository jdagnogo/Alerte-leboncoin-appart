package com.example.jdagnogo.alertlebonsoinappart.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.jdagnogo.alertlebonsoinappart.AlertLEboncoinApplication;
import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.adapter.ResultResearchAppartAdapter;
import com.example.jdagnogo.alertlebonsoinappart.models.Appart;
import com.example.jdagnogo.alertlebonsoinappart.models.RequestItems;
import com.example.jdagnogo.alertlebonsoinappart.models.realm.AppartRealm;
import com.example.jdagnogo.alertlebonsoinappart.models.realm.RequestItemsRealm;
import com.example.jdagnogo.alertlebonsoinappart.models.realm.SearchRealm;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.GlobalBus;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.UpdateAppartsBus;
import com.example.jdagnogo.alertlebonsoinappart.services.jobs.DemoJobCreator;
import com.example.jdagnogo.alertlebonsoinappart.services.jobs.GetLastAppartJob;
import com.example.jdagnogo.alertlebonsoinappart.services.retrofit.RetrofitNetworkInterface;
import com.example.jdagnogo.alertlebonsoinappart.utils.Parser;
import com.example.jdagnogo.alertlebonsoinappart.utils.TransitionUtils;
import com.veinhorn.scrollgalleryview.ScrollGalleryView;

import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.NAME_RESEARCH;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.NEW_RESEARCH;

public class ResultActivity extends FragmentActivity {
    private final static String TAG = ResultActivity.class.getCanonicalName();

    private List<Appart> appart;
    private RecyclerView recycleListView;
    private ResultResearchAppartAdapter adapter;
    private Realm realm;
    private HashMap<String, String> map;
    private int jobId;
    private RequestItemsRealm requestItemsRealm;
    private String searchName = "";
    @Bind(R.id.alarm)
    FloatingActionButton alarm;
    @Bind(R.id.no_result)
    LinearLayout noResult;
    @Inject
    Retrofit retrofit;
    GetLastAppartJob demoSyncJob;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionUtils.doTranstion(getWindow());
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        progressBar.setVisibility(View.VISIBLE);
        appart = new ArrayList<>();
        realm = ((AlertLEboncoinApplication) getApplication()).getRealm();

        initJob();
        GlobalBus.getBus().register(this);
        RequestItems requestItems;

        if (getIntent() != null) {
            requestItems = getIntent().getParcelableExtra(NEW_RESEARCH);
            searchName = getIntent().getExtras().getString(NAME_RESEARCH);

            ((AlertLEboncoinApplication) getApplication()).getNetworkComponent().inject(ResultActivity.this);
            map = requestItems.createHashMap();

            requestItemsRealm = new RequestItemsRealm(requestItems);
            getAppart(map);


        }

    }

    private void hideOrDisplayAlarm() {
        realm = ((AlertLEboncoinApplication) getApplication()).getRealm();
        realm.beginTransaction();
        RealmResults<SearchRealm> results =
                realm.where(SearchRealm.class).findAll();

        for (int i = 0; i < results.size(); i++) {
            RequestItems r1 = results.get(i).getRequestItemsRealm().getRequestItem();
            if (requestItemsRealm.getRequestItem().equals(r1)) {
                alarm.setVisibility(View.GONE);
                break;
            }
        }
        realm.commitTransaction();
        realm.close();
    }

    @OnClick(R.id.alarm)
    public void setOnAlarmClick() {
        SearchRealm searchRealm = new SearchRealm(searchName, new Date(), new AppartRealm(appart.get(0)), requestItemsRealm);
        //searchRealm.setJobID(jobId);
        addSearchToDb(searchRealm);
        Number nextID = (realm.where(SearchRealm.class).max("id"));
        int id = 0;
        if (null != nextID) {
            id = nextID.intValue();
        }
        Log.e("job : ", "nextID au job " + id);
        jobId = demoSyncJob.scheduleJob(id);
        searchRealm.setJobID(jobId);
        updateSearchInDb(searchRealm);
        alarm.setVisibility(View.GONE);
        realm.close();
    }

    private void initJob() {
        DemoJobCreator demoJobCreator = new DemoJobCreator();
        demoJobCreator.create(GetLastAppartJob.TAG);
        demoSyncJob = new GetLastAppartJob();
    }

    private void initRecycler() {
        recycleListView = (RecyclerView) findViewById(R.id.recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycleListView.setLayoutManager(mLayoutManager);
        adapter = new ResultResearchAppartAdapter(this);
        recycleListView.setAdapter(adapter);
        adapter.setData(appart);
        adapter.notifyDataSetChanged();
    }

    private void getAppart(final HashMap<String, String> params) {
        RetrofitNetworkInterface mService = retrofit.create(RetrofitNetworkInterface.class);
        Call<ResponseBody> mSong = mService.getApparts(params);
        mSong.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d("url", "url : " + call.request().url());
                    List<Appart> appart = Parser.parseHtml(response);
                    UpdateAppartsBus event = new UpdateAppartsBus(appart);
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

    private void updateSearchInDb(SearchRealm searchRealm) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(searchRealm);
        realm.commitTransaction();
    }

    private void addSearchToDb(SearchRealm searchRealm) {
        realm.beginTransaction();
        Number nextID = (realm.where(SearchRealm.class).max("id"));
        int id = 0;
        if (null != nextID) {
            id = nextID.intValue();
        }
        searchRealm.setId(id + 1);
        realm.copyToRealmOrUpdate(searchRealm);
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
        progressBar.setVisibility(View.GONE);
        if (updateSwipeViewBus.getApparts().size() != 0) {
            appart = updateSwipeViewBus.getApparts();
            initRecycler();
            noResult.setVisibility(View.GONE);
            alarm.setVisibility(View.VISIBLE);
            hideOrDisplayAlarm();
            //showAppartDetails();

        } else {
            noResult.setVisibility(View.VISIBLE);
            alarm.setVisibility(View.GONE);
        }
    }

}
