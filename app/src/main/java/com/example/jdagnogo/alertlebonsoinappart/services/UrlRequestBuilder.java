package com.example.jdagnogo.alertlebonsoinappart.services;

import com.example.jdagnogo.alertlebonsoinappart.enums.City;
import com.example.jdagnogo.alertlebonsoinappart.enums.Meuble;
import com.example.jdagnogo.alertlebonsoinappart.enums.Type;
import com.example.jdagnogo.alertlebonsoinappart.models.RequestItems;

import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.BASE_URL;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.BASE_URL_FORMAT;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.CODE_POSTAL_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_CODE_POSTAL;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_RENT_MAX;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_RENT_MIN;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_ROOM_MAX;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_ROOM_MIN;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_SURFACE_MAX;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_SURFACE_MIN;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.KEY_WORD_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.LOCATION_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.MEUBLE_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.MORE_CITIES_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.MORE_OPTIONS;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.MORE_OPTIONS_FORMAT;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.RENT_MAX_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.RENT_MIN_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.ROOM_MAX_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.ROOM_MIN_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.SPACE;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.SPACE_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.SURFACE_MAX_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.SURFACE_MIN_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.TYPE_KEY;


public class UrlRequestBuilder {

    public static String createUrl(RequestItems requestItems) {
        return String.format(BASE_URL_FORMAT, BASE_URL, generateRequestOptions(requestItems));
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
        if (requestItems.getRentMin() != DEFAULT_RENT_MIN){
            stringBuilder.append(generateRightFormatRequest(RENT_MIN_KEY,requestItems.getRentMin()));
        }
        //max
        if (requestItems.getRentMax() != DEFAULT_RENT_MAX){
            stringBuilder.append(generateRightFormatRequest(RENT_MAX_KEY,requestItems.getRentMax()));
        }
        // Surface
        //min
        if (requestItems.getSurfaceMin() != DEFAULT_SURFACE_MIN){
            stringBuilder.append(generateRightFormatRequest(SURFACE_MIN_KEY,requestItems.getSurfaceMin()));
        }
        //max
        if (requestItems.getSurfaceMax() != DEFAULT_SURFACE_MAX){
            stringBuilder.append(generateRightFormatRequest(SURFACE_MAX_KEY,requestItems.getSurfaceMax()));
        }
        // Room
        //min
        if (requestItems.getRoomMin() != DEFAULT_ROOM_MIN){
            stringBuilder.append(generateRightFormatRequest(ROOM_MIN_KEY,requestItems.getRoomMin()));
        }
        //max
        if (requestItems.getRoomMax() != DEFAULT_ROOM_MAX){
            stringBuilder.append(generateRightFormatRequest(ROOM_MAX_KEY,requestItems.getRoomMax()));
        }
        return stringBuilder.toString();
    }



}
