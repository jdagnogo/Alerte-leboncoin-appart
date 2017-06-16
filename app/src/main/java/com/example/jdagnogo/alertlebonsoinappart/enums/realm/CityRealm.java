package com.example.jdagnogo.alertlebonsoinappart.enums.realm;

import com.example.jdagnogo.alertlebonsoinappart.enums.City;

import io.realm.RealmObject;

public class CityRealm extends RealmObject {
    private String enumDescription;

    public void saveEnum(City val) {
        this.enumDescription = val.toString();
    }

    public City getEnum() {
        return (enumDescription != null) ? City.valueOf(enumDescription) : null;
    }
}