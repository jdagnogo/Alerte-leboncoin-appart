package com.example.jdagnogo.alertlebonsoinappart.services;

import com.example.jdagnogo.alertlebonsoinappart.enums.City;
import com.example.jdagnogo.alertlebonsoinappart.enums.Meuble;
import com.example.jdagnogo.alertlebonsoinappart.enums.Type;
import com.example.jdagnogo.alertlebonsoinappart.models.RequestItems;

import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.BASE_URL_FORMAT;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.CODE_POSTAL_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_CODE_POSTAL;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.KEY_WORD_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.LOCATION_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.MEUBLE_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.MORE_CITIES_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.MORE_OPTIONS;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.MORE_OPTIONS_FORMAT;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.SPACE;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.SPACE_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.TYPE_KEY;


public class UrlRequestBuilder {

    public static String createUrl(RequestItems requestItems) {
        return String.format(BASE_URL_FORMAT,generateRequestOptions(requestItems));
    }

    private static String generateRightFormatRequest(String key, Object val){
        return String.format(MORE_OPTIONS_FORMAT,key,val,MORE_OPTIONS);
    }
    private static String generateRequestOptions(RequestItems requestItems) {
        StringBuilder stringBuilder = new StringBuilder();

        // City
        stringBuilder.append(LOCATION_KEY);
        for (City city : requestItems.getCities()) {
            stringBuilder.append(city.getCode());
            if (city.getCodePostal() != DEFAULT_CODE_POSTAL) {
                stringBuilder.append(CODE_POSTAL_KEY);
                stringBuilder.append(city.getCodePostal());
            }
            stringBuilder.append(MORE_CITIES_KEY);
        }
        stringBuilder.append(MORE_OPTIONS);

        //Meuble
        if (!requestItems.getMeuble().equals(Meuble.MEUBLE_DEFAULT)) {
            stringBuilder.append(generateRightFormatRequest(MEUBLE_KEY,requestItems.getMeuble().getValue()));
        }
        // Type
        if (!requestItems.getType().equals(Type.APPARTEMENT_DEFAULT)){
            stringBuilder.append(generateRightFormatRequest(TYPE_KEY,requestItems.getType().getValue()));
        }
        // Key word
        if (!requestItems.getKeyWord().isEmpty()){
            stringBuilder.append(generateRightFormatRequest(KEY_WORD_KEY,requestItems.getKeyWord().replaceAll(SPACE,SPACE_KEY)));
        }
        // Rent
        //min
       /* if (requestItems.getRent().getMin() != DEFAULT_RENT_MIN){
            stringBuilder.append(generateRightFormatRequest(RENT_MIN_KEY,requestItems.getRent().getMin()));
        }
        //max
        if (requestItems.getRent().getMax() != DEFAULT_RENT_MAX){
            stringBuilder.append(generateRightFormatRequest(RENT_MAX_KEY,requestItems.getRent().getMax()));
        }
        // Surface
        //min
        if (requestItems.getSurface().getMin() != DEFAULT_SURFACE_MIN){
            stringBuilder.append(generateRightFormatRequest(SURFACE_MIN_KEY,requestItems.getSurface().getMin()));
        }
        //max
        if (requestItems.getSurface().getMax() != DEFAULT_SURFACE_MAX){
            stringBuilder.append(generateRightFormatRequest(SURFACE_MAX_KEY,requestItems.getSurface().getMax()));
        }
        // Room
        //min
        if (requestItems.getNbRoom().getMin() != DEFAULT_ROOM_MIN){
            stringBuilder.append(generateRightFormatRequest(ROOM_MIN_KEY,requestItems.getNbRoom().getMin()));
        }
        //max
        if (requestItems.getNbRoom().getMax() != DEFAULT_ROOM_MAX){
            stringBuilder.append(generateRightFormatRequest(ROOM_MAX_KEY,requestItems.getNbRoom().getMax()));
        }*/
        return stringBuilder.toString();
    }



}
