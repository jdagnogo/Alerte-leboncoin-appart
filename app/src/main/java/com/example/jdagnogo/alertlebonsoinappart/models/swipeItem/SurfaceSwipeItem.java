package com.example.jdagnogo.alertlebonsoinappart.models.swipeItem;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.jdagnogo.alertlebonsoinappart.AlertLEboncoinApplication;
import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.models.MinMaxItemAbstract;

import java.util.HashMap;

/**
 * Created by Jeff on 02/05/2017.
 */

public class SurfaceSwipeItem extends SwipeItemAbstract implements Parcelable{

    public SurfaceSwipeItem() {

        this.postitonMin=0;
        this.unite = " m²";
        this.minTitle = AlertLEboncoinApplication.getContext().getString(R.string.surface_title_min);
        this.maxTitle =AlertLEboncoinApplication.getContext().getString(R.string.surface_title_max);
        this.title =  AlertLEboncoinApplication.getContext().getString(R.string.surface_title);
        map = new HashMap<Integer, String>() {
            {
                put(0, "Min");
                put(1, "10");
                put(2, "20");
                put(3, "30");
                put(4, "40");
                put(5, "50");
                put(6, "60");
                put(7, "70");
                put(8, "80");
                put(9, "90");
                put(10, "Max");
            }
        };
        this.positionMax =map.size()-1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.minMaxItemAbstract, flags);
        dest.writeInt(this.postitonMin);
        dest.writeInt(this.positionMax);
        dest.writeString(this.description);
    }

    protected SurfaceSwipeItem(Parcel in) {
        super(in);
        this.minMaxItemAbstract = in.readParcelable(MinMaxItemAbstract.class.getClassLoader());
        this.postitonMin = in.readInt();
        this.positionMax = in.readInt();
        this.description = in.readString();
    }

    public static final Creator<SurfaceSwipeItem> CREATOR = new Creator<SurfaceSwipeItem>() {
        @Override
        public SurfaceSwipeItem createFromParcel(Parcel source) {
            return new SurfaceSwipeItem(source);
        }

        @Override
        public SurfaceSwipeItem[] newArray(int size) {
            return new SurfaceSwipeItem[size];
        }
    };
}
