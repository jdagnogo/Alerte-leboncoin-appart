package com.example.jdagnogo.alertlebonsoinappart.services.eventbus;

/**
 * Created by Jeff on 02/05/2017.
 */

import org.greenrobot.eventbus.EventBus;

public class GlobalBus {
    private static EventBus sBus;
    public static EventBus getBus() {
        if (sBus == null)
            sBus = EventBus.getDefault();
        return sBus;
    }
}
