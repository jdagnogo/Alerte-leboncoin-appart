package com.example.jdagnogo.alertlebonsoinappart.utils.swipeItem;

import com.example.jdagnogo.alertlebonsoinappart.AlertLEboncoinApplication;
import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.models.DialogMinMaxBeans;
import com.roughike.swipeselector.SwipeItem;

/**
 * Created by Jeff on 02/05/2017.
 */

public class SurfaceSwipeItem  {

    private static SwipeItem[] createSwipeItem(String title) {
        return new SwipeItem[]{new SwipeItem(0, title, "0 m²"),
                new SwipeItem(1, title, "20 m²"),
                new SwipeItem(2, title, "30 m²"),
                new SwipeItem(3, title, "40 m²"),
                new SwipeItem(4, title, "50 m²"),
                new SwipeItem(5, title, "60 m²")
        };
    }

    public static DialogMinMaxBeans createBeans(int position) {
        return rentDialogMinBean();
    }

    public static DialogMinMaxBeans rentDialogMinBean() {
        DialogMinMaxBeans fake = new DialogMinMaxBeans();
        fake.setTitle(AlertLEboncoinApplication.getContext().getString(R.string.surface));
        String minTitle = AlertLEboncoinApplication.getContext().getString(R.string.surface_title_min);
        String maxTitle = AlertLEboncoinApplication.getContext().getString(R.string.surface_title_max);
        fake.setSwipeMin(createSwipeItem(minTitle));
        fake.setSwipeMax(createSwipeItem(maxTitle));
        return fake;
    }
}
