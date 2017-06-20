package com.example.jdagnogo.alertlebonsoinappart.services.jobs;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

public class DemoJobCreator implements JobCreator {

    @Override
    public Job create(String tag) {
        switch (tag) {
            case GetLastAppartJob.TAG:
                return new GetLastAppartJob();
            default:
                return null;
        }
    }
}