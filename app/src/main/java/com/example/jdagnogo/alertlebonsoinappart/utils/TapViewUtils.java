package com.example.jdagnogo.alertlebonsoinappart.utils;

import android.app.Activity;
import android.view.View;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;

import jonathanfinerty.once.Once;

/**
 * Created by Jeff on 28/08/2017.
 */

public class TapViewUtils {
    private Activity activity;
    private String title;
    private String sub;
    private View target;

    public TapViewUtils( Activity activity, String title, String sub, View target) {
        this.activity = activity;
        this.title = title;
        this.sub = sub;
        this.target = target;
    }

    public void doAnimation(){
        if (!Once.beenDone(Once.THIS_APP_INSTALL, activity.getClass().getSimpleName())) {
            TapTargetView.showFor(activity,                 // `this` is an Activity
                    TapTarget.forView(target, title, sub)
                            // All options below are a// Specify a color for the outer circle
                            .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                            .titleTextSize(28)                  // Specify the size (in sp) of the title text// Specify the color of the title text
                            .descriptionTextSize(20)            // Specify the size (in sp) of the description text
                            .drawShadow(true)                   // Whether to draw a drop shadow or not
                            .cancelable(true)                  // Whether tapping outside the outer circle dismisses the view
                            .tintTarget(true)                   // Whether to tint the target view's color
                            .transparentTarget(true)           // Specify whether the target is transparent (displays the content underneath)
                            .targetRadius(60)                  // Specify the target radius (in dp)
            );
            Once.markDone(activity.getClass().getSimpleName());
        }

    }
}
