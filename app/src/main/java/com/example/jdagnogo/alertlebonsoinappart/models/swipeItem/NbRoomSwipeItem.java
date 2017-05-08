package com.example.jdagnogo.alertlebonsoinappart.models.swipeItem;

import com.example.jdagnogo.alertlebonsoinappart.AlertLEboncoinApplication;
import com.example.jdagnogo.alertlebonsoinappart.R;

import java.util.HashMap;


/**
 * Created by Jeff on 02/05/2017.
 */

public class NbRoomSwipeItem extends SwipeItemAbstract {

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
}
