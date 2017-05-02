package com.example.jdagnogo.alertlebonsoinappart.models;

/**
 * Created by Jeff on 02/05/2017.
 */

public abstract class SwipeItemAbstract {
    protected int min;
    protected int max;

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
