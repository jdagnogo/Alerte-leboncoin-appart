package com.example.jdagnogo.alertlebonsoinappart.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jeff on 02/05/2017.
 */

public abstract class MinMaxItemAbstract implements Parcelable{
    protected String positionMin;
    protected String positionMax;

    public String getPositionMin() {
        return positionMin;
    }

    public void setPositionMin(String positionMin) {
        this.positionMin = positionMin;
    }

    public String getPositionMax() {
        return positionMax;
    }

    public void setPositionMax(String positionMax) {
        this.positionMax = positionMax;
    }

    public MinMaxItemAbstract(String positionMin, String positionMax) {
        this.positionMin = positionMin;
        this.positionMax = positionMax;
    }

    public MinMaxItemAbstract() {
        this.positionMax = "0";
        this.positionMin = "0";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.positionMin);
        dest.writeString(this.positionMax);
    }

    protected MinMaxItemAbstract(Parcel in) {
        this.positionMin = in.readString();
        this.positionMax = in.readString();
    }

}
