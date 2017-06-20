package com.example.jdagnogo.alertlebonsoinappart.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.adapter.ResearchAdapter;
import com.example.jdagnogo.alertlebonsoinappart.adapter.ResultResearchAppartAdapter;
import com.example.jdagnogo.alertlebonsoinappart.models.Search;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Jeff on 18/06/2017.
 */

public class MainActivity extends Activity{
    private RecyclerView recycleListView;
    private ResearchAdapter adapter;
    private List<Search>searches;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        searches = new ArrayList<>();
        initRecycler();
    }
    private void initRecycler() {
        recycleListView = (RecyclerView) findViewById(R.id.recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycleListView.setLayoutManager(mLayoutManager);
        adapter = new ResearchAdapter();
        recycleListView.setAdapter(adapter);
        adapter.setData(searches);
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
