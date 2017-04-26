package com.example.jdagnogo.alertlebonsoinappart.services;

import com.example.jdagnogo.alertlebonsoinappart.enums.City;
import com.example.jdagnogo.alertlebonsoinappart.models.RequestItems;

public class UrlRequestBuilder {

    private final static String BASE_URL = "www.leboncoin.fr/locations/offres/ile_de_france/occasions/?th=1&parrot=0&";

    public static String createUrl(RequestItems requestItems) {
        return String.format("%s%s",BASE_URL,generateRequestOptions(requestItems));
    }

    private static String generateRequestOptions(RequestItems requestItems) {
        StringBuilder stringBuilder = new StringBuilder();
        // cities
        for (City city :requestItems.getCities()){
            stringBuilder.append(city.getCityName())
        }
        return stringBuilder.toString();
    }
}
