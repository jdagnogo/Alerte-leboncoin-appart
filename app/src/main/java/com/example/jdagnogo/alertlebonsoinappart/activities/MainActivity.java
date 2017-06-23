package com.example.jdagnogo.alertlebonsoinappart.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jdagnogo.alertlebonsoinappart.AlertLEboncoinApplication;
import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.adapter.ResearchAdapter;
import com.example.jdagnogo.alertlebonsoinappart.models.Search;
import com.example.jdagnogo.alertlebonsoinappart.models.realm.SearchRealm;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Jeff on 18/06/2017.
 */

public class MainActivity extends Activity{
    private RecyclerView recycleListView;
    private ResearchAdapter adapter;
    private List<Search> searches;
    private Realm realm;

    @Override
    protected void onResume() {
        super.onResume();
        searches = new ArrayList<>();
        realm = ((AlertLEboncoinApplication) getApplication()).getRealm();
        realm.beginTransaction();
        RealmQuery<SearchRealm> query = realm.where(SearchRealm.class);
        final RealmResults<SearchRealm> resultRealm = query.findAll();
        for (int i =0;i<resultRealm.size();i++){
            searches.add(resultRealm.get(i).getSearch());
        }
        realm.commitTransaction();
        realm.close();
        initRecycler();
    }

    @Bind(R.id.new_research)
    FloatingActionButton newResearch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.new_research)
    public void setOnNewResearchClick() {
        Intent intent = new Intent(this, NewSearchActivity.class);
        startActivity(intent);
    }
    private void initRecycler() {
        recycleListView = (RecyclerView) findViewById(R.id.recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycleListView.setLayoutManager(mLayoutManager);
        adapter = new ResearchAdapter(this);
        recycleListView.setAdapter(adapter);
        adapter.setData(searches);
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            realm.close();
            realm = null;
        }
    }
}
