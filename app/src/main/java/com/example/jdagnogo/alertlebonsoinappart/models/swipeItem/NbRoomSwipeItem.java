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

public class NbRoomSwipeItem extends SwipeItemAbstract implements Parcelable{

    public NbRoomSwipeItem() {

        this.postitonMin=0;
        this.unite = "";
        this.minTitle = AlertLEboncoinApplication.getContext().getString(R.string.nb_room);
        this.maxTitle =AlertLEboncoinApplication.getContext().getString(R.string.nb_room_title_max);
        this.title =  AlertLEboncoinApplication.getContext().getString(R.string.nb_room_title_min);
        map = new HashMap<Integer, String>() {
            {
                put(0, "Min");
                put(1, "1");
                put(2, "2");
                put(3, "3");
                put(4, "4");
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

    protected NbRoomSwipeItem(Parcel in) {
        super(in);
        this.minMaxItemAbstract = in.readParcelable(MinMaxItemAbstract.class.getClassLoader());
        this.postitonMin = in.readInt();
        this.positionMax = in.readInt();
        this.description = in.readString();
    }

    public static final Creator<NbRoomSwipeItem> CREATOR = new Creator<NbRoomSwipeItem>() {
        @Override
        public NbRoomSwipeItem createFromParcel(Parcel source) {
            return new NbRoomSwipeItem(source);
        }

        @Override
        public NbRoomSwipeItem[] newArray(int size) {
            return new NbRoomSwipeItem[size];
        }
    };
}
