package com.example.jdagnogo.alertlebonsoinappart.services.eventbus;

import com.example.jdagnogo.alertlebonsoinappart.enums.*;

/**
 * Created by Jeff on 02/05/2017.
 */

public class UpdateSwipeViewBus {
        private SwipeItemEnum swipeItemEnum;
    private String min;
    private String max;

        public UpdateSwipeViewBus(SwipeItemEnum swipeItemEnum,String min , String max) {
            this.swipeItemEnum = swipeItemEnum;
            this.min = min;
            this.max = max;
        }

        public SwipeItemEnum getSwipeItemEnum() {
            return swipeItemEnum;
        }

    public void setSwipeItemEnum(SwipeItemEnum swipeItemEnum) {
        this.swipeItemEnum = swipeItemEnum;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }
}
