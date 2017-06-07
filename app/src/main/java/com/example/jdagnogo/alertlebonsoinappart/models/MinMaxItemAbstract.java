package com.example.jdagnogo.alertlebonsoinappart.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jeff on 02/05/2017.
 */

public abstract class MinMaxItemAbstract implements Parcelable{
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.positionMin);
        dest.writeInt(this.positionMax);
    }

    protected MinMaxItemAbstract(Parcel in) {
        this.positionMin = in.readInt();
        this.positionMax = in.readInt();
    }


}
