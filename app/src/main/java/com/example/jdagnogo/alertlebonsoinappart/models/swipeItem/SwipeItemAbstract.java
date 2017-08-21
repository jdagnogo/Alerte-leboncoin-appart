package com.example.jdagnogo.alertlebonsoinappart.models.swipeItem;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.jdagnogo.alertlebonsoinappart.models.DialogMinMaxBeans;
import com.example.jdagnogo.alertlebonsoinappart.models.MinMaxItemAbstract;
import com.roughike.swipeselector.SwipeItem;

import java.util.Map;

/**
 * Created by Jeff on 04/05/2017.
 */

public abstract class SwipeItemAbstract implements Parcelable{
    protected MinMaxItemAbstract minMaxItemAbstract;
    int postitonMin;
    int positionMax;
    String description;
    static String unite;
    static String title;
    static String minTitle;
    static String maxTitle;
     Map<Integer, String> map;

    public SwipeItemAbstract(MinMaxItemAbstract minMaxItemAbstract, int postitonMin, int positionMax, String description) {
        this.minMaxItemAbstract = minMaxItemAbstract;
        this.postitonMin = postitonMin;
        this.positionMax = positionMax;
        this.description = description;
    }

    private  SwipeItem[] createSwipeItem(String title) {
        SwipeItem[] swipeItems = new SwipeItem[map.size()];
        for (int i = 0; i < map.size(); i++) {
            swipeItems[i] = new SwipeItem(i, title, map.get(i) + unite);
        }
        return swipeItems;
    }

    public static String getUnite() {
        return unite;
    }

    public DialogMinMaxBeans createBeans() {
        return rentDialogMinBean();
    }

    public  DialogMinMaxBeans rentDialogMinBean() {
        DialogMinMaxBeans fake = new DialogMinMaxBeans();
        fake.setTitle(title);
        fake.setSwipeMin(createSwipeItem(minTitle));
        fake.setSwipeMax(createSwipeItem(maxTitle));
        return fake;
    }

    protected SwipeItemAbstract() {
    }

    public MinMaxItemAbstract getMinMaxItemAbstract() {
        return minMaxItemAbstract;
    }

    public void setMinMaxItemAbstract(MinMaxItemAbstract minMaxItemAbstract) {
        this.minMaxItemAbstract = minMaxItemAbstract;
    }

    public int getPostitonMin() {
        return postitonMin;
    }

    public void setPostitonMin(int postitonMin) {
        this.postitonMin = postitonMin;
    }

    public int getPositionMax() {
        return positionMax;
    }

    public String getDescription(int position){
        if (position == 0 || position == map.size()) {
            return map.get(position);
        }
        else {
            return map.get(position)+getUnite();
        }
    }

    public String getDescriptionMinWithUnity() {
        if (postitonMin != 0) {

            return map.get(postitonMin)+getUnite();
        }else return map.get(postitonMin);

    }
    public String getDescriptionMin() {
        if (postitonMin != 0) {

            return map.get(postitonMin);
        }else return map.get(postitonMin);

    }
    public String getDescriptionMax() {
        if (positionMax != map.size()) {

            return map.get(positionMax);
        }else return map.get(positionMax);
    }
    public String getDescriptionMaxWithUnity() {
        if (positionMax != map.size()) {

            return map.get(positionMax)+getUnite();
        }else return map.get(positionMax);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPositionMax(int positionMax) {
        this.positionMax = positionMax;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.minMaxItemAbstract, flags);
        dest.writeInt(this.postitonMin);
        dest.writeInt(this.positionMax);
        dest.writeString(this.description);
    }

    protected SwipeItemAbstract(Parcel in) {
        this.minMaxItemAbstract = in.readParcelable(MinMaxItemAbstract.class.getClassLoader());
        this.postitonMin = in.readInt();
        this.positionMax = in.readInt();
        this.description = in.readString();
    }


}
