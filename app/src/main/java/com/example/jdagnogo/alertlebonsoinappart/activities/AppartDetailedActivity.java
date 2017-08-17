package com.example.jdagnogo.alertlebonsoinappart.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.jdagnogo.alertlebonsoinappart.AlertLEboncoinApplication;
import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.models.Appart;
import com.example.jdagnogo.alertlebonsoinappart.models.AppartDetails;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.GlobalBus;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.UpdateAppartDetailedBus;
import com.example.jdagnogo.alertlebonsoinappart.services.retrofit.RetrofitNetworkInterface;
import com.example.jdagnogo.alertlebonsoinappart.utils.GlideImageLoader;
import com.example.jdagnogo.alertlebonsoinappart.utils.Parser;
import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.ScrollGalleryView;

import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.APPART;

public class AppartDetailedActivity extends FragmentActivity {
    AppartDetails appartDetails;
    Appart appart;
    @Bind(R.id.title)
    TextView title;
    @Inject
    Retrofit retrofit;
    private ScrollGalleryView scrollGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appart_detailed);
        ButterKnife.bind(this);
        GlobalBus.getBus().register(this);
        if (getIntent() != null) {
            appart = getIntent().getParcelableExtra(APPART);
            title.setText(appart.getTitle());
            ((AlertLEboncoinApplication) getApplication()).getNetworkComponent().inject(AppartDetailedActivity.this);
            getAppartDetailed();
        }
    }

    private void getAppartDetailed() {
        RetrofitNetworkInterface mService = retrofit.create(RetrofitNetworkInterface.class);
        // TODO : check if it is a valid int
        Call<ResponseBody> responseBodyCall = mService.getAppartsDetailed(appart.getUrlDesc());
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d("url", "url : " + call.request().url());
                    appartDetails = Parser.parseAppartDetailed(response, appart);
                    UpdateAppartDetailedBus event = new UpdateAppartDetailedBus(appartDetails);
                    GlobalBus.getBus().post(event);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("AppartDetailsActivity", "Display error code " + t.toString());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GlobalBus.getBus().unregister(this);
    }

    @Subscribe
    public void getAppartDetailed(UpdateAppartDetailedBus updateAppartDetailedBus) {
        List<MediaInfo> infos = new ArrayList<>(appartDetails.getImgsUrl().size());

        for (String url : appartDetails.getImgsUrl()) {
            infos.add(MediaInfo.mediaLoader(new GlideImageLoader(url)));
        }
        scrollGalleryView = (ScrollGalleryView) findViewById(R.id.scroll_gallery_view);
        scrollGalleryView
                .setThumbnailSize(100)
                .setZoom(true)
                .setFragmentManager(getSupportFragmentManager())
                .addMedia(infos);
    }
}