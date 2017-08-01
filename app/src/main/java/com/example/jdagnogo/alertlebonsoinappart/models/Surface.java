package com.example.jdagnogo.alertlebonsoinappart.models;

import android.os.Parcel;

import com.example.jdagnogo.alertlebonsoinappart.models.realm.SurfaceRealm;

/**
 * Created by Jeff on 02/05/2017.
 */

public class Surface extends MinMaxItemAbstract {
    public Surface(String min, String max) {
        super(min, max);
    }
    public Surface(){

    }

    public Surface(SurfaceRealm surfaceRealm){
        this.positionMax= surfaceRealm.getPositionMax();
        this.positionMin = surfaceRealm.getPositionMin();

    }
    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if (this == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        // Class name is Employ & have lastname
        Surface surface = (Surface) obj ;
        return (this.positionMin.equals(surface.getPositionMin())
                &&
                this.positionMax.equals(surface.getPositionMax()));
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
