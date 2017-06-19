package com.example.jdagnogo.alertlebonsoinappart.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.jdagnogo.alertlebonsoinappart.R;

import butterknife.ButterKnife;

/**
 * Created by Jeff on 18/06/2017.
 */

public class MainActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
