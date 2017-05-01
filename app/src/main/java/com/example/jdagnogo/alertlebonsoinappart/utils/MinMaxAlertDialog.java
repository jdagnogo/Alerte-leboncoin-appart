package com.example.jdagnogo.alertlebonsoinappart.utils;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.models.DialogMinMaxBeans;
import com.roughike.swipeselector.OnSwipeItemSelectedListener;
import com.roughike.swipeselector.SwipeItem;
import com.roughike.swipeselector.SwipeSelector;

/**
 * Created by Jeff on 01/05/2017.
 */

public class MinMaxAlertDialog {
    private  static SwipeSelector selectorMin;
    private static SwipeSelector selectorMax;
    private Activity activity;
    private  static DialogMinMaxBeans dialogMinMaxBeans;

    public MinMaxAlertDialog(DialogMinMaxBeans dialogMinMaxBeans,Activity activity){
       this.activity =activity;
        this.dialogMinMaxBeans = dialogMinMaxBeans;
    }


    public void createDialogMinRent() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.min_max_alert_dialog, null);

        TextView title = (TextView) dialogView.findViewById(R.id.title);
        title.setText(dialogMinMaxBeans.getTitle());

        selectorMin = (SwipeSelector) dialogView.findViewById(R.id.min);
        selectorMax = (SwipeSelector) dialogView.findViewById(R.id.max);

        selectorMin.setItems(dialogMinMaxBeans.getSwipeMin());
        selectorMin.setOnItemSelectedListener(minOnSwipeItemSelectedListener());


        selectorMax.setItems(dialogMinMaxBeans.getSwipeMax());
        selectorMax.setOnItemSelectedListener(maxOnSwipeItemSelectedListener());


        Button ok = (Button) dialogView.findViewById(R.id.ok);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        Button cancel = (Button) dialogView.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("toto", "id : " + (int) selectorMin.getSelectedItem().value);
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private static OnSwipeItemSelectedListener maxOnSwipeItemSelectedListener() {
        return new OnSwipeItemSelectedListener() {
            @Override
            public void onItemSelected(SwipeItem item) {
                int min = (int) selectorMin.getSelectedItem().value;
                int max = (int) selectorMax.getSelectedItem().value;
                if (min < max && min != 0) {
                    selectorMin.setOnItemSelectedListener(null);
                    selectorMin.selectItemAt(min - 1);
                    selectorMin.setOnItemSelectedListener(minOnSwipeItemSelectedListener());
                }
            }
        };
    }

    private static OnSwipeItemSelectedListener minOnSwipeItemSelectedListener() {
        return new OnSwipeItemSelectedListener() {
            @Override
            public void onItemSelected(SwipeItem item) {
                int min = (int) selectorMin.getSelectedItem().value;
                int max = (int) selectorMax.getSelectedItem().value;
                if (min > max && (min + 1) < dialogMinMaxBeans.getSwipeMin().length) {
                    selectorMax.setOnItemSelectedListener(null);
                    selectorMax.selectItemAt(min + 1);
                    selectorMax.setOnItemSelectedListener(maxOnSwipeItemSelectedListener());
                }
            }
        };
    }
}
