package com.example.jdagnogo.alertlebonsoinappart.services.jobs;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;
import com.example.jdagnogo.alertlebonsoinappart.AlertLEboncoinApplication;
import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.models.Appart;
import com.example.jdagnogo.alertlebonsoinappart.models.Search;
import com.example.jdagnogo.alertlebonsoinappart.services.retrofit.RetrofitNetworkInterface;
import com.example.jdagnogo.alertlebonsoinappart.utils.Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.NOTIFICATION_SERVICE;

public class DemoSyncJob extends Job {

    public static final String TAG = "job_demo_tag";
    @Inject
    Retrofit retrofit;
    private  HashMap<String, String> map;
    String result = "";

    @Override
    @NonNull
    protected Result onRunJob(Params params) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {

                Context context = AlertLEboncoinApplication.getContext();
                ((AlertLEboncoinApplication) context).getNetworkComponent().inject(DemoSyncJob.this);
                // use System.currentTimeMillis() to have a unique ID for the pending intent
                getAppart();

                // build notification
                // the addAction re-use the same intent to keep the example short

            }
        });
        return Result.SUCCESS;
    }

    public  void scheduleJob(HashMap<String, String> map) {
        this.map = map;
        new JobRequest.Builder(DemoSyncJob.TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(5))
                .setPersisted(true)
                .build()
                .schedule();
    }

    private void getAppart() {
        final Context context = AlertLEboncoinApplication.getContext();

        RetrofitNetworkInterface mService = retrofit.create(RetrofitNetworkInterface.class);
        Call<ResponseBody> mSong = mService.getApparts(map);
        mSong.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "Result " + response.body().string());
                    result = "" + response.code();
                    Document document = Jsoup.parse(response.body().string());
                    Elements ensemble = document.getElementsByClass("list_item");
                    List<Appart> apparts =Parser.parseHtml(ensemble);

                    //realm
                    Realm.init(context);
                    Realm realm = Realm.getInstance(getRealmConfig());
                    RealmQuery<Search> query = realm.where(Search.class);
                    RealmResults<Search> result = query.findAll();

                    if (!result.get(0).getLastAppart().equals(apparts.get(0))){
                        Intent intent = new Intent();
                        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);
                        Notification n = new Notification.Builder(context)
                                .setContentTitle("alerte lebon coin")
                                .setContentText("new appart !!  "+result.get(0).getLastAppart().getTitle())
                                .setSmallIcon(R.drawable.test)
                                .setContentIntent(pIntent)
                                .setAutoCancel(true)
                                .addAction(R.drawable.test, "Call", pIntent)
                                .addAction(R.drawable.test, "More", pIntent)
                                .addAction(R.drawable.test, "And more", pIntent).build();


                        NotificationManager notificationManager =
                                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

                        notificationManager.notify(0, n);
                    }else {
                        Intent intent = new Intent();
                        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);
                        Notification n = new Notification.Builder(context)
                                .setContentTitle("alerte lebon coin")
                                .setContentText("pas de nouveautés... "+apparts.get(0).getTitle())
                                .setSmallIcon(R.drawable.test)
                                .setContentIntent(pIntent)
                                .setAutoCancel(true)
                                .addAction(R.drawable.test, "Call", pIntent)
                                .addAction(R.drawable.test, "More", pIntent)
                                .addAction(R.drawable.test, "And more", pIntent).build();


                        NotificationManager notificationManager =
                                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

                        notificationManager.notify(0, n);
                    }

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

    public static RealmConfiguration getRealmConfig() {
        return  new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
    }
}
