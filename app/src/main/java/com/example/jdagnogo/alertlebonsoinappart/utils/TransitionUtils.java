package com.example.jdagnogo.alertlebonsoinappart.utils;

import android.transition.Fade;
import android.view.Window;

public class TransitionUtils {

    public final static void doTranstion(Window window) {

        Fade fade = new Fade();
        fade.setDuration(1000);
        window.setReturnTransition(fade);

    }
}
