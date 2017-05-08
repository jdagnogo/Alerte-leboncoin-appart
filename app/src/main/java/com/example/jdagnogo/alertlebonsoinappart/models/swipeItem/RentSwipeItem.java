package com.example.jdagnogo.alertlebonsoinappart.models.swipeItem;

import com.example.jdagnogo.alertlebonsoinappart.AlertLEboncoinApplication;
import com.example.jdagnogo.alertlebonsoinappart.R;

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

}