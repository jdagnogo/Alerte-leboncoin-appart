package com.example.jdagnogo.alertlebonsoinappart.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.jdagnogo.alertlebonsoinappart.R;
import com.webianks.library.scroll_choice.ScrollChoice;

import java.util.ArrayList;
import java.util.List;

public class NewSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_search);

        ScrollChoice scrollChoice = (ScrollChoice) findViewById(R.id.scroll_choice);
        final ScrollChoice scrollChoice2 = (ScrollChoice) findViewById(R.id.scroll_choice2);


        final List<String> data = new ArrayList<>();
        data.add("0");
        data.add("100");
        data.add("200");
        data.add("300");
        data.add("400");
        data.add("500");

        scrollChoice.addItems(data,0);
        scrollChoice2.addItems(data,5);
        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                Log.d("webi",name);

            }
        });

        scrollChoice.setOnItemSelectedListener(new ScrollChoice.OnItemSelectedListener() {
            @Override
            public void onItemSelected(ScrollChoice scrollChoice, int position, String name) {
                Log.d("webi",name);
            }
        });
    }
}
