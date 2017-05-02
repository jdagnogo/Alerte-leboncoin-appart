package com.example.jdagnogo.alertlebonsoinappart.services.eventbus;

import com.example.jdagnogo.alertlebonsoinappart.enums.*;

/**
 * Created by Jeff on 02/05/2017.
 */

public class UpdateSwipeViewBus {
        private SwipeItemEnum swipeItemEnum;
    private int min;
    private int max;

        public UpdateSwipeViewBus(SwipeItemEnum swipeItemEnum, int min, int max) {
            this.swipeItemEnum = swipeItemEnum;
        }

        public SwipeItemEnum getSwipeItemEnum() {
            return swipeItemEnum;
        }

    public void setSwipeItemEnum(SwipeItemEnum swipeItemEnum) {
        this.swipeItemEnum = swipeItemEnum;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
