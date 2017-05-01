package com.example.jdagnogo.alertlebonsoinappart.models;

import com.roughike.swipeselector.SwipeItem;

/**
 * Created by Jeff on 01/05/2017.
 */
public class DialogMinMaxBeans {
    String title;
    SwipeItem[] swipeMin;
    SwipeItem[] swipeMax;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SwipeItem[] getSwipeMin() {
        return swipeMin;
    }

    public void setSwipeMin(SwipeItem[] swipeMin) {
        this.swipeMin = swipeMin;
    }


    public SwipeItem[] getSwipeMax() {
        return swipeMax;
    }

    public void setSwipeMax(SwipeItem[] swipeMax) {
        this.swipeMax = swipeMax;
    }
}
