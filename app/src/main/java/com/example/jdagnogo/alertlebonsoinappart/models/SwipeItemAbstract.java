package com.example.jdagnogo.alertlebonsoinappart.models;

/**
 * Created by Jeff on 02/05/2017.
 */

public abstract class SwipeItemAbstract {
    protected String min;
    protected String max;

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
    public SwipeItemAbstract(String min, String max){
        this.min = min;
        this.max = max;
    }
    public SwipeItemAbstract(){

    }
}
