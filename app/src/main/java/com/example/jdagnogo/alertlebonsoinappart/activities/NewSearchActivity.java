package com.example.jdagnogo.alertlebonsoinappart.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.models.DialogMinMaxBeans;
import com.example.jdagnogo.alertlebonsoinappart.utils.MinMaxAlertDialog;
import com.roughike.swipeselector.SwipeItem;

import butterknife.ButterKnife;

public class NewSearchActivity extends AppCompatActivity {
    // @Bind(R.id.rent)
    //LinearLayout rent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_search);
        ButterKnife.bind(this);
        LinearLayout rentLayout = (LinearLayout) findViewById(R.id.rent);
        rentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MinMaxAlertDialog minMaxAlertDialog = new MinMaxAlertDialog(fake(),NewSearchActivity.this);
                minMaxAlertDialog.createDialogMinRent();
            }
        });

    }

    private DialogMinMaxBeans fake() {
        DialogMinMaxBeans fake = new DialogMinMaxBeans();
        fake.setTitle("Loyer");
        SwipeItem[] swipeItem = new SwipeItem[]{new SwipeItem(0, "Slide one", "Description for slide one."),
                new SwipeItem(1, "Slide two", "Description for slide two."),
                new SwipeItem(2, "Slide three", "Description for slide three.")
        };
        fake.setSwipeMin(swipeItem);
        fake.setSwipeMax(swipeItem);
        return fake;
    }



}
