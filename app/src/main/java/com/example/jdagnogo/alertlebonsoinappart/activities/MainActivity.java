package com.example.jdagnogo.alertlebonsoinappart.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.example.jdagnogo.alertlebonsoinappart.AlertLEboncoinApplication;
import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.adapter.ResearchAdapter;
import com.example.jdagnogo.alertlebonsoinappart.models.Search;
import com.example.jdagnogo.alertlebonsoinappart.models.realm.SearchRealm;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.DeleteSearchBus;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.GlobalBus;
import com.example.jdagnogo.alertlebonsoinappart.services.jobs.DemoJobCreator;
import com.example.jdagnogo.alertlebonsoinappart.utils.Constants;
import com.example.jdagnogo.alertlebonsoinappart.utils.TapViewUtils;
import com.example.jdagnogo.alertlebonsoinappart.utils.TransitionUtils;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import jonathanfinerty.once.Amount;
import jonathanfinerty.once.Once;

/**
 * Created by Jeff on 18/06/2017.
 */

public class MainActivity extends FragmentActivity implements RatingDialogListener{
    private RecyclerView recycleListView;
    private ResearchAdapter adapter;
    private List<Search> searches;
    private Realm realm;
    @Bind(R.id.no_search)
    LinearLayout noSearch;
    @Bind(R.id.new_research)
    FloatingActionButton newResearch;

    @Override
    protected void onResume() {
        super.onResume();

        getSearchesFromDB();
        initRecycler();
        TapViewUtils tapViewUtils = new TapViewUtils(this, getString(R.string.titre_newsearch_tuto), getString(R.string.sub_newsearch_tuto), findViewById(R.id.new_research));
        tapViewUtils.doAnimation();
        showDialog();
    }

    private void getSearchesFromDB() {
        searches = new ArrayList<>();
        realm = ((AlertLEboncoinApplication) getApplication()).getRealm();
        realm.beginTransaction();
        RealmQuery<SearchRealm> query = realm.where(SearchRealm.class);
        final RealmResults<SearchRealm> resultRealm = query.findAll();
        for (int i = 0; i < resultRealm.size(); i++) {
            searches.add(resultRealm.get(i).getSearch());
        }
        realm.commitTransaction();
        realm.close();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionUtils.doTranstion(getWindow());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        GlobalBus.getBus().register(this);


    }

    @OnClick(R.id.new_research)
    public void setOnNewResearchClick() {

        Intent intent = new Intent(this, NewSearchActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        startActivity(intent, options.toBundle());
    }

    private void initRecycler() {
        recycleListView = (RecyclerView) findViewById(R.id.recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycleListView.setLayoutManager(mLayoutManager);
        adapter = new ResearchAdapter(this);
        recycleListView.setAdapter(adapter);
        adapter.setData(searches);
        adapter.notifyDataSetChanged();

        if (0 == searches.size()) {
            noSearch.setVisibility(View.VISIBLE);
        } else {
            noSearch.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Once.markDone("deco");
        GlobalBus.getBus().unregister(this);
        if (realm != null) {
            realm.close();
            realm = null;
        }
    }
    private void showDialog() {
        if (Once.beenDone("deco", Amount.exactly(1))) {
            new AppRatingDialog.Builder()
                    .setPositiveButtonText("Valider")
                    .setNegativeButtonText("Annuler")
                    .setDefaultRating(2)
                    .setTitle("Noter cette application")
                    .setDescription("Selectionne une des étoiles et donne moi un feedback !")
                    .setTitleTextColor(R.color.colorPrimary)
                    .setDescriptionTextColor(R.color.colorPrimary)
                    .setHint("Laisse un commentaire ici ...")
                    .setHintTextColor(R.color.colorPrimary)
                    .setCommentTextColor(R.color.colorPrimary)
                    .setCommentBackgroundColor(R.color.colorPrimaryLight)
                    .setWindowAnimation(R.style.MyDialogFadeAnimation)
                    .create(MainActivity.this)
                    .show();
        }

    }
    @Subscribe
    public void deleteSearch(DeleteSearchBus deleteSearchBus) {
        realm = ((AlertLEboncoinApplication) getApplication()).getRealm();
        realm.beginTransaction();
        RealmResults<SearchRealm> results =
                realm.where(SearchRealm.class)
                        .equalTo("id", deleteSearchBus.getSearch().getId()).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
        DemoJobCreator demoJobCreator = new DemoJobCreator();
        demoJobCreator.cancelJob(deleteSearchBus.getSearch().getJobID());

        getSearchesFromDB();
        initRecycler();
    }

    @Override
    public void onPositiveButtonClicked(int i, @NotNull String s) {
        Answers.getInstance().logCustom(new CustomEvent(Constants.RATING).putCustomAttribute(Constants.RATING,"nb étoiles : "+i));
        if (null!= s && !s.isEmpty())
        Answers.getInstance().logCustom(new CustomEvent(Constants.COMMENT).putCustomAttribute(Constants.COMMENT,s));
    }

    @Override
    public void onNegativeButtonClicked() {

    }
}
