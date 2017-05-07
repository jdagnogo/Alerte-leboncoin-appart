package com.example.jdagnogo.alertlebonsoinappart.models;

/**
 * Created by Jeff on 02/05/2017.
 */

public abstract class MinMaxItemAbstract {
    protected int positionMin;
    protected int positionMax;

    public int getPositionMin() {
        return positionMin;
    }

    public void setPositionMin(int positionMin) {
        this.positionMin = positionMin;
    }

    public int getPositionMax() {
        return positionMax;
    }

    public void setPositionMax(int positionMax) {
        this.positionMax = positionMax;
    }

    public MinMaxItemAbstract(int positionMin, int positionMax) {
        this.positionMin = positionMin;
        this.positionMax = positionMax;
    }

    public MinMaxItemAbstract() {
        this.positionMax = 0;
        this.positionMin = 0;
    }
}
