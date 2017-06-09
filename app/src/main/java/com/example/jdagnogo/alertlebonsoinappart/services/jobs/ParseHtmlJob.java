package com.example.jdagnogo.alertlebonsoinappart.services.jobs;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseHtmlJob extends Job {
    private String responseBody;
    public static final String TAG = "parse_html_job";
    @NonNull
    @Override
    protected Result onRunJob(Params params) {
        Document document = Jsoup.parse(responseBody);
        Elements element = document.select("toto");
        return Result.SUCCESS;
    }

    public ParseHtmlJob(String responseBody) {
        this.responseBody = responseBody;
    }
    public static void scheduleJob() {
        new JobRequest.Builder(ParseHtmlJob.TAG)
                .setExact(1)
                .build()
                .schedule();
    }
}
