package com.example.jdagnogo.alertlebonsoinappart.models.swipeItem;

import com.example.jdagnogo.alertlebonsoinappart.models.DialogMinMaxBeans;
import com.example.jdagnogo.alertlebonsoinappart.models.MinMaxItemAbstract;
import com.roughike.swipeselector.SwipeItem;

import java.util.Map;

/**
 * Created by Jeff on 04/05/2017.
 */

public abstract class SwipeItemAbstract {
    protected MinMaxItemAbstract minMaxItemAbstract;
    int postitonMin;
    int positionMax;
    String description;
    static String unite;
    static String title;
    static String minTitle;
    static String maxTitle;
    static Map<Integer, String> map;

    public SwipeItemAbstract(MinMaxItemAbstract minMaxItemAbstract, int postitonMin, int positionMax, String description) {
        this.minMaxItemAbstract = minMaxItemAbstract;
        this.postitonMin = postitonMin;
        this.positionMax = positionMax;
        this.description = description;
    }

    private static SwipeItem[] createSwipeItem(String title) {
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

    public String getDescriptionMin() {
        if (postitonMin != 0) {

            return map.get(postitonMin)+getUnite();
        }else return map.get(postitonMin);

    }

    public String getDescriptionMax() {
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


}
