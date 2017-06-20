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

import java.io.IOException;
import java.util.Date;
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

public class GetLastAppartJob extends Job {

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
                ((AlertLEboncoinApplication) context).getNetworkComponent().inject(GetLastAppartJob.this);
                id = params.getExtras().getInt("id", 33);
                Log.e("job : ","id récupéré : "+id);
                getAppart();

            }
        });
        return Result.SUCCESS;
    }

    public int scheduleJob(int id) {
        this.id = id;
        PersistableBundleCompat extras = new PersistableBundleCompat();
        extras.putInt("id", id);
        Log.e("job : ","id dans l extras : "+id);

        int jobId =  new JobRequest.Builder(GetLastAppartJob.TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(5))
                .setPersisted(true)
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .setExtras(extras)
                .build()
                .schedule();

        return jobId;


    }

    private void getAppart() {
        final Context context = AlertLEboncoinApplication.getContext();

        RetrofitNetworkInterface mService = retrofit.create(RetrofitNetworkInterface.class);
        //realm
        Realm.init(context);
        final Realm realm = Realm.getInstance(getRealmConfig());
        RealmQuery<Search> query = realm.where(Search.class).equalTo("id", id);
        final RealmResults<Search> resultRealm = query.findAll();
        Log.e("job : ","resultRealm last appart:"+resultRealm.get(0).getLastAppart().getTitle());

        Call<ResponseBody> mSong = mService.getApparts(resultRealm.get(0).getRequestItemsRealm().getRequestItem().createHashMap());
        mSong.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    List<Appart> appartsFromHtml = Parser.parseHtml(response);
                    if (!resultRealm.get(0).getLastAppart().getTitle().equals(appartsFromHtml.get(0).getTitle())) {
                        Intent intent = new Intent();
                        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);

                        Notification n = new Notification.Builder(context)
                                .setContentTitle("alerte lebon coin")
                                .setContentText("new appart !!  " + appartsFromHtml.get(0).getTitle())
                                .setSmallIcon(R.drawable.test)
                                .setContentIntent(pIntent)
                                .setAutoCancel(true)
                                .addAction(R.drawable.test, "Call", pIntent)
                                .addAction(R.drawable.test, "More", pIntent)
                                .addAction(R.drawable.test, "And more", pIntent).build();


                        NotificationManager notificationManager =
                                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

                        notificationManager.notify(0, n);

                       final Search search = new Search(resultRealm.get(0).getId(),
                                resultRealm.get(0).getTitle(),
                                resultRealm.get(0).getRequestItemsRealm()
                                ,new Date(), appartsFromHtml.get(0));
                        try { // I could use try-with-resources here
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    realm.insertOrUpdate(search);
                                }
                            });
                        } finally {
                            if(realm != null) {
                                realm.close();
                            }
                        }
                    }else {
                        Log.e("job : ","same, appartHtml last appart:"+appartsFromHtml.get(0).getTitle());
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
