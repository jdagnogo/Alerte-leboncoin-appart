package com.example.jdagnogo.alertlebonsoinappart.models;

import android.os.Parcel;

/**
 * Created by Jeff on 02/05/2017.
 */

public class Surface extends MinMaxItemAbstract {
    public Surface(String min, String max) {
        super(min, max);
    }
    public Surface(){

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

    protected Surface(Parcel in) {
        super(in);
        this.positionMin = in.readString();
        this.positionMax = in.readString();
    }

    public static final Creator<Surface> CREATOR = new Creator<Surface>() {
        @Override
        public Surface createFromParcel(Parcel source) {
            return new Surface(source);
        }

        @Override
        public Surface[] newArray(int size) {
            return new Surface[size];
        }
    };
}
