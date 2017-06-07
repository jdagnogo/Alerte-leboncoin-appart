package com.example.jdagnogo.alertlebonsoinappart.models;

import android.os.Parcel;

import com.example.jdagnogo.alertlebonsoinappart.AlertLEboncoinApplication;
import com.example.jdagnogo.alertlebonsoinappart.R;
import com.roughike.swipeselector.SwipeItem;

/**
 * Created by Jeff on 02/05/2017.
 */

public class NbRoom extends MinMaxItemAbstract {
    public NbRoom(int min, int max) {
        super(min, max);
    }
    public NbRoom(){

    }

    private static SwipeItem[] createSwipeItem(String title) {
        return new SwipeItem[]{new SwipeItem(0, title, "0"),
                new SwipeItem(1, title, "1"),
                new SwipeItem(2, title, "2"),
                new SwipeItem(3, title, "3"),
                new SwipeItem(4, title, "4"),
                new SwipeItem(5, title, "5")
        };
    }

    public static DialogMinMaxBeans createBeans(int position) {
        return rentDialogMinBean();
    }

    public static DialogMinMaxBeans rentDialogMinBean() {
        DialogMinMaxBeans fake = new DialogMinMaxBeans();
        fake.setTitle(AlertLEboncoinApplication.getContext().getString(R.string.nb_room));
        String minTitle = AlertLEboncoinApplication.getContext().getString(R.string.nb_room_title_min);
        String maxTitle = AlertLEboncoinApplication.getContext().getString(R.string.nb_room_title_max);
        fake.setSwipeMin(createSwipeItem(minTitle));
        fake.setSwipeMax(createSwipeItem(maxTitle));
        return fake;
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

    protected NbRoom(Parcel in) {
        super(in);
        this.positionMin = in.readInt();
        this.positionMax = in.readInt();
    }

    public static final Creator<NbRoom> CREATOR = new Creator<NbRoom>() {
        @Override
        public NbRoom createFromParcel(Parcel source) {
            return new NbRoom(source);
        }

        @Override
        public NbRoom[] newArray(int size) {
            return new NbRoom[size];
        }
    };
}
