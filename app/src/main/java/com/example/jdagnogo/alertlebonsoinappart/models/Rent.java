package com.example.jdagnogo.alertlebonsoinappart.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.jdagnogo.alertlebonsoinappart.models.realm.RentRealm;

/**
 * Created by Jeff on 02/05/2017.
 */

public class Rent extends MinMaxItemAbstract implements Parcelable {

    public Rent(String min, String max) {
        super(min, max);
    }
    public Rent(){
    }
    public Rent(RentRealm rentRealm){
        this.positionMax= rentRealm.getPositionMax();
        this.positionMin = rentRealm.getPositionMin();

    }
    public static String getDescription(){
        return "";
    }
    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if (this == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        // Class name is Employ & have lastname
        Rent rent = (Rent) obj ;
        return (this.positionMin.equals(rent.getPositionMin())
                &&
                this.positionMax.equals(rent.getPositionMax()));
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
