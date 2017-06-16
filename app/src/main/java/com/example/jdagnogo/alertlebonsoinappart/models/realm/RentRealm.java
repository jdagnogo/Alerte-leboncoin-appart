package com.example.jdagnogo.alertlebonsoinappart.models.realm;

import com.example.jdagnogo.alertlebonsoinappart.models.Rent;

import io.realm.RealmObject;

public class RentRealm extends RealmObject {

    private String positionMin;
    private String positionMax;

    public RentRealm(){

    }
    public RentRealm(Rent rent){
        this.positionMax= rent.getPositionMax();
        this.positionMin = rent.getPositionMin();

    }


    public String getPositionMin() {
        return positionMin;
    }

    public void setPositionMin(String positionMin) {
        this.positionMin = positionMin;
    }

    public String getPositionMax() {
        return positionMax;
    }

    public void setPositionMax(String positionMax) {
        this.positionMax = positionMax;
    }
}
