package com.example.jdagnogo.alertlebonsoinappart.utils.swipeItem;

import com.example.jdagnogo.alertlebonsoinappart.AlertLEboncoinApplication;
import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.models.DialogMinMaxBeans;
import com.roughike.swipeselector.SwipeItem;

/**
 * Created by Jeff on 02/05/2017.
 */

public class RentSwipeItem{

    private static SwipeItem[] createSwipeItem(String title) {
        return new SwipeItem[]{new SwipeItem(0, title, "0 €"),
                new SwipeItem(1, title, "100 €"),
                new SwipeItem(2, title, "200 €"),
                new SwipeItem(3, title, "300 €"),
                new SwipeItem(4, title, "400 €"),
                new SwipeItem(5, title, "500 €")
        };
    }

    public static DialogMinMaxBeans createBeans(int position) {
       return rentDialogMinBean();
    }

    public static DialogMinMaxBeans rentDialogMinBean() {
        DialogMinMaxBeans fake = new DialogMinMaxBeans();
        fake.setTitle(AlertLEboncoinApplication.getContext().getString(R.string.rent));
        String minTitle = AlertLEboncoinApplication.getContext().getString(R.string.rent_title_min);
        String maxTitle = AlertLEboncoinApplication.getContext().getString(R.string.rent_title_max);
        fake.setSwipeMin(createSwipeItem(minTitle));
        fake.setSwipeMax(createSwipeItem(maxTitle));
        return fake;
    }
}