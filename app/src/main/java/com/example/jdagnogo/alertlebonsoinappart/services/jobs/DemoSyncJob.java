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
import com.evernote.android.job.util.support.PersistableBundleCompat;
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
import java.util.List;

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
    private int id;


    @Override
    @NonNull
    protected Result onRunJob(final Params params) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {

                Context context = AlertLEboncoinApplication.getContext();
                ((AlertLEboncoinApplication) context).getNetworkComponent().inject(DemoSyncJob.this);
                // use System.currentTimeMillis() to have a unique ID for the pending intent
                id = params.getExtras().getInt("id", 33);
                getAppart();

                // build notification
                // the addAction re-use the same intent to keep the example short

            }
        });
        return Result.SUCCESS;
    }

    public void scheduleJob(int id) {
        this.id = id;
        PersistableBundleCompat extras = new PersistableBundleCompat();
        extras.putInt("id", id);

        new JobRequest.Builder(DemoSyncJob.TAG)
                .setExecutionWindow(10_000L, 15_000L)
                .build()
                .schedule();
    }

    private void getAppart() {
        final Context context = AlertLEboncoinApplication.getContext();

        RetrofitNetworkInterface mService = retrofit.create(RetrofitNetworkInterface.class);
        //realm
        Realm.init(context);
        Realm realm = Realm.getInstance(getRealmConfig());
        RealmQuery<Search> query = realm.where(Search.class).equalTo("id", id);
        final RealmResults<Search> result = query.findAll();

        Call<ResponseBody> mSong = mService.getApparts(result.get(0).getRequestItemsRealm().getRequestItem().createHashMap());
        mSong.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Document document = Jsoup.parse(response.body().string());
                    Elements ensemble = document.getElementsByClass("list_item");
                    List<Appart> apparts = Parser.parseHtml(ensemble);


                    if (!result.get(0).getLastAppart().getTitle().equals(apparts.get(0).getTitle())) {
                        Intent intent = new Intent();
                        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);
                        Notification n = new Notification.Builder(context)
                                .setContentTitle("alerte lebon coin")
                                .setContentText("new appart !!  " + result.get(0).getLastAppart().getTitle())
                                .setSmallIcon(R.drawable.test)
                                .setContentIntent(pIntent)
                                .setAutoCancel(true)
                                .addAction(R.drawable.test, "Call", pIntent)
                                .addAction(R.drawable.test, "More", pIntent)
                                .addAction(R.drawable.test, "And more", pIntent).build();


                        NotificationManager notificationManager =
                                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

                        notificationManager.notify(0, n);
                    } else {
                        Intent intent = new Intent();
                        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);
                        Notification n = new Notification.Builder(context)
                                .setContentTitle("alerte lebon coin")
                                .setContentText("pas de nouveautés... " + apparts.get(0).getTitle())
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
        return new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
    }
}
