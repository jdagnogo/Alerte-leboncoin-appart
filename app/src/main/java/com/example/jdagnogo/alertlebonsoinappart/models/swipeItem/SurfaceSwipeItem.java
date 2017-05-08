package com.example.jdagnogo.alertlebonsoinappart.models.swipeItem;

import com.example.jdagnogo.alertlebonsoinappart.AlertLEboncoinApplication;
import com.example.jdagnogo.alertlebonsoinappart.R;

import java.util.HashMap;

/**
 * Created by Jeff on 02/05/2017.
 */

public class SurfaceSwipeItem extends SwipeItemAbstract{

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
                put(5, "Max");
            }
        };
        this.positionMax =map.size()-1;
    }
}
