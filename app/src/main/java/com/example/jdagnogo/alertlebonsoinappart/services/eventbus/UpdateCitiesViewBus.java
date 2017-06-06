package com.example.jdagnogo.alertlebonsoinappart.services.eventbus;

import com.example.jdagnogo.alertlebonsoinappart.enums.*;

import java.util.List;

/**
 * Created by Jeff on 02/05/2017.
 */

public class UpdateCitiesViewBus {
    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    private List<City> cities;

    public UpdateCitiesViewBus(List<City> cities) {
        this.cities =cities;
    }


}
