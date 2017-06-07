package com.example.jdagnogo.alertlebonsoinappart.models;

import android.os.Parcel;

/**
 * Created by Jeff on 02/05/2017.
 */

public class Surface extends MinMaxItemAbstract {
    public Surface(int min, int max) {
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
        dest.writeInt(this.positionMin);
        dest.writeInt(this.positionMax);
    }

    protected Surface(Parcel in) {
        super(in);
        this.positionMin = in.readInt();
        this.positionMax = in.readInt();
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
