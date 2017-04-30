package com.example.jdagnogo.alertlebonsoinappart.utils;

import android.content.Context;

/**
 * Created by Jeff on 30/04/2017.
 */

public class CompatUtils {
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
