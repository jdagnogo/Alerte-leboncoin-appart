package com.example.jdagnogo.alertlebonsoinappart.utils;

import com.example.jdagnogo.alertlebonsoinappart.models.DialogMinMaxBeans;
import com.roughike.swipeselector.SwipeItem;
import com.example.jdagnogo.alertlebonsoinappart.*;

public class Constants {

    //...........// UrlRequest   //....................//
    public final static String BASE_URL = "www.leboncoin.fr/locations/offres/ile_de_france/occasions/?th=1&parrot=0&";
    public final static String BASE_URL_FORMAT = "%s%s";
    public final static String MORE_OPTIONS_FORMAT = "%s%s%s";
    public final static String CODE_POSTAL_KEY = "%20";
    public final static String LOCATION_KEY = "location=";
    public final static String TYPE_KEY = "ret=";
    public final static String KEY_WORD_KEY = "q=";
    public final static String RENT_MIN_KEY = "mrs=";
    public final static String RENT_MAX_KEY = "mre=";
    public final static String SURFACE_MIN_KEY = "sqs=";
    public final static String SURFACE_MAX_KEY = "sqe=";
    public final static String ROOM_MIN_KEY = "ros=";
    public final static String ROOM_MAX_KEY = "roe=";
    public final static String SPACE = " ";
    public final static String SPACE_KEY = "%20";
    public final static String MEUBLE_KEY = "furn=";
    public final static String MORE_CITIES_KEY = "%2C";
    public final static String MORE_OPTIONS = "&";


    //...........// enums   //....................//

    public final static int DEFAULT_CODE_POSTAL = 0;
    public final static int DEFAULT_RENT_MIN = 0;
    public final static int DEFAULT_RENT_MAX = 9999;
    public final static int DEFAULT_SURFACE_MIN = 0;
    public final static int DEFAULT_SURFACE_MAX = 9999;
    public final static int DEFAULT_ROOM_MIN = 0;
    public final static int DEFAULT_ROOM_MAX = 9999;
    //...........// models   //....................//


    //...........// Activities   //....................//

    public static DialogMinMaxBeans rentDialogMinBean() {
        DialogMinMaxBeans fake = new DialogMinMaxBeans();
        fake.setTitle(AlertLEboncoinApplication.getContext().getString(R.string.rent_title));
        String minTitle = AlertLEboncoinApplication.getContext().getString(R.string.rent_title_min);
        String maxTitle = AlertLEboncoinApplication.getContext().getString(R.string.rent_title_max);
        fake.setSwipeMin(createSwipeItem(minTitle));
        fake.setSwipeMax(createSwipeItem(maxTitle));
        return fake;
    }

    private static SwipeItem[] createSwipeItem(String title) {
        return new SwipeItem[]{new SwipeItem(0, title, "0 €"),
                new SwipeItem(1, title, "100 €"),
                new SwipeItem(1, title, "200 €"),
                new SwipeItem(1, title, "300 €"),
                new SwipeItem(1, title, "400 €"),
                new SwipeItem(2, title, "500 €")
        };
    }
}
