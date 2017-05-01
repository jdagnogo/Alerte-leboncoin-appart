package com.example.jdagnogo.alertlebonsoinappart.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.utils.Constants;
import com.example.jdagnogo.alertlebonsoinappart.utils.MinMaxAlertDialog;

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
                MinMaxAlertDialog minMaxAlertDialog = new MinMaxAlertDialog(Constants.rentDialogMinBean(),NewSearchActivity.this);
                minMaxAlertDialog.createDialogMinRent();
            }
        });

    }




}
