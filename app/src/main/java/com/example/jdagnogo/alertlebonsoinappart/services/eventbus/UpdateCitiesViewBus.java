package com.example.jdagnogo.alertlebonsoinappart.services.eventbus;

import com.example.jdagnogo.alertlebonsoinappart.enums.City;

import java.util.List;

/**
 * Created by Jeff on 02/05/2017.
 */

public class UpdateCitiesViewBus {
    public City getcity() {
        return city;
    }

    public void setcity(City city) {
        this.city = city;
    }

    private City city;

    public UpdateCitiesViewBus(City city) {
        this.city =city;
    }


}
