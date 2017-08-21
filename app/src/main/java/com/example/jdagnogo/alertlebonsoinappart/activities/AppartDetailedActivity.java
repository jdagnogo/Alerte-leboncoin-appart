package com.example.jdagnogo.alertlebonsoinappart.activities;

import android.content.Intent;
import android.net.Uri;
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
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.APPART;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.BASE_URL;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.SITE_URL;

public class AppartDetailedActivity extends FragmentActivity {
    AppartDetails appartDetails;
    Appart appart;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.price)
    TextView price;
    @Bind(R.id.nb_room)
    TextView nbRoom;
    @Bind(R.id.surface)
    TextView surface;
    @Bind(R.id.redirection)
    TextView redirection;
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

    private void initView() {
        title.setText(appartDetails.getTitle());
        surface.setText(appartDetails.getSurface());
        price.setText(appartDetails.getPrice());
        nbRoom.setText(appartDetails.getNbRoom());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GlobalBus.getBus().unregister(this);
    }

    @OnClick(R.id.redirection)
    public void onRedirectionClick() {
        String url = String.format("%s%s.htm?ca=2_s",SITE_URL,appartDetails.getUrlDesc());
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @Subscribe
    public void getAppartDetailed(UpdateAppartDetailedBus updateAppartDetailedBus) {

        scrollGalleryView = (ScrollGalleryView) findViewById(R.id.scroll_gallery_view);
        initView();
        if (0 < appartDetails.getImgsUrl().size()) {
            List<MediaInfo> infos = new ArrayList<>(appartDetails.getImgsUrl().size());
            for (String url : appartDetails.getImgsUrl()) {
                infos.add(MediaInfo.mediaLoader(new GlideImageLoader(url)));
            }

            scrollGalleryView
                    .setThumbnailSize(100)
                    .setZoom(true)
                    .setFragmentManager(getSupportFragmentManager())
                    .addMedia(infos);
        } else {
            MediaInfo info = MediaInfo.mediaLoader(new GlideImageLoader("http:" + appartDetails.getImage()));
            scrollGalleryView
                    .setThumbnailSize(100)
                    .setZoom(true)
                    .setFragmentManager(getSupportFragmentManager())
                    .addMedia(info);
        }
    }
}