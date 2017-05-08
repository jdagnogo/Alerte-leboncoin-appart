package com.example.jdagnogo.alertlebonsoinappart.utils;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.enums.SwipeItemEnum;
import com.example.jdagnogo.alertlebonsoinappart.models.DialogMinMaxBeans;
import com.example.jdagnogo.alertlebonsoinappart.models.swipeItem.SwipeItemAbstract;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.GlobalBus;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.UpdateSwipeViewBus;
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
    private static OnSwipeItemSelectedListener minSwipe;
    private static OnSwipeItemSelectedListener maxSwipe;
    private static int positionMin;
    private static int positionMax;
    private static SwipeItemEnum swipeItemEnum;

    public MinMaxAlertDialog(DialogMinMaxBeans dialogMinMaxBeans,Activity activity,SwipeItemEnum swipeItemEnum,SwipeItemAbstract swipeItemAbstract,int positionMin,int positionMax){
       this.activity =activity;
        this.dialogMinMaxBeans = dialogMinMaxBeans;
        minSwipe = minOnSwipeItemSelectedListener();
        maxSwipe = maxOnSwipeItemSelectedListener();
        this.positionMin = positionMin;
        this.positionMax = positionMax;
        this.swipeItemEnum = swipeItemEnum;
    }


    public void createDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.min_max_alert_dialog, null);

        TextView title = (TextView) dialogView.findViewById(R.id.title);
        title.setText(dialogMinMaxBeans.getTitle());

        selectorMin = (SwipeSelector) dialogView.findViewById(R.id.min);
        selectorMax = (SwipeSelector) dialogView.findViewById(R.id.max);

        selectorMin.setItems(dialogMinMaxBeans.getSwipeMin());
        selectorMin.selectItemAt(positionMin);
        selectorMin.setOnItemSelectedListener(minSwipe);


        selectorMax.setItems(dialogMinMaxBeans.getSwipeMax());
        selectorMax.selectItemAt(positionMax);
        selectorMax.setOnItemSelectedListener(maxSwipe);



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
                int min = (Integer) selectorMin.getSelectedItem().value;
                int max = (Integer) selectorMax.getSelectedItem().value;
                UpdateSwipeViewBus updateSwipeViewBus =
                        new UpdateSwipeViewBus(swipeItemEnum,min,max);
                GlobalBus.getBus().post(updateSwipeViewBus);
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private static OnSwipeItemSelectedListener maxOnSwipeItemSelectedListener() {
        return new OnSwipeItemSelectedListener() {
            @Override
            public void onItemSelected(SwipeItem item) {
                int min = (Integer) selectorMin.getSelectedItem().value;
                int max = (Integer) selectorMax.getSelectedItem().value;
                if (min > max ) {
                    selectorMin.setOnItemSelectedListener(null);
                    selectorMin.selectItemAt(max);
                    selectorMin.setOnItemSelectedListener(minSwipe);
                }
            }
        };
    }

    private static OnSwipeItemSelectedListener minOnSwipeItemSelectedListener() {
        return new OnSwipeItemSelectedListener() {
            @Override
            public void onItemSelected(SwipeItem item) {
                int min = (Integer) selectorMin.getSelectedItem().value;
                int max = (Integer) selectorMax.getSelectedItem().value;
                if (min > max ) {
                    selectorMax.setOnItemSelectedListener(null);
                    selectorMax.selectItemAt(min);
                    selectorMax.setOnItemSelectedListener(maxSwipe);
                }
            }
        };
    }
}
