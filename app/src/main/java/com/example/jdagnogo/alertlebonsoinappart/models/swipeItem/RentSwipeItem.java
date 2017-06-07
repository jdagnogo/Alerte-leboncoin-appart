package com.example.jdagnogo.alertlebonsoinappart.models.swipeItem;

import android.os.Parcel;

import com.example.jdagnogo.alertlebonsoinappart.AlertLEboncoinApplication;
import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.models.MinMaxItemAbstract;

import java.util.HashMap;


/**
 * Created by Jeff on 02/05/2017.
 */

public class RentSwipeItem extends SwipeItemAbstract {
    public RentSwipeItem() {

        this.postitonMin=0;
        this.unite = " €";
        this.minTitle = AlertLEboncoinApplication.getContext().getString(R.string.rent_title);
        this.maxTitle =AlertLEboncoinApplication.getContext().getString(R.string.rent_title_max);
        this.title =  AlertLEboncoinApplication.getContext().getString(R.string.rent_title_min);
         map = new HashMap<Integer, String>() {
            {
                put(0, "Min");
                put(1, "100");
                put(2, "200");
                put(3, "300");
                put(4, "400");
                put(5, "Max");
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

    protected RentSwipeItem(Parcel in) {
        super(in);
        this.minMaxItemAbstract = in.readParcelable(MinMaxItemAbstract.class.getClassLoader());
        this.postitonMin = in.readInt();
        this.positionMax = in.readInt();
        this.description = in.readString();
    }

    public static final Creator<RentSwipeItem> CREATOR = new Creator<RentSwipeItem>() {
        @Override
        public RentSwipeItem createFromParcel(Parcel source) {
            return new RentSwipeItem(source);
        }

        @Override
        public RentSwipeItem[] newArray(int size) {
            return new RentSwipeItem[size];
        }
    };
}