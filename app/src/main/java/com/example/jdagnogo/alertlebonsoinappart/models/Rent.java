package com.example.jdagnogo.alertlebonsoinappart.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jeff on 02/05/2017.
 */

public class Rent extends MinMaxItemAbstract implements Parcelable {

    public Rent(String min, String max) {
        super(min, max);
    }
    public Rent(){
    }

    public static String getDescription(){
        return "";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.positionMin);
        dest.writeString(this.positionMax);
    }

    protected Rent(Parcel in) {
        super(in);
        this.positionMin = in.readString();
        this.positionMax = in.readString();
    }

    public static final Creator<Rent> CREATOR = new Creator<Rent>() {
        @Override
        public Rent createFromParcel(Parcel source) {
            return new Rent(source);
        }

        @Override
        public Rent[] newArray(int size) {
            return new Rent[size];
        }
    };
}
