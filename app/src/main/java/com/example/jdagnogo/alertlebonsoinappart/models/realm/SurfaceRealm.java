package com.example.jdagnogo.alertlebonsoinappart.models.realm;

import com.example.jdagnogo.alertlebonsoinappart.models.Surface;

import io.realm.RealmObject;

public class SurfaceRealm extends RealmObject {

    private String positionMin;
    private String positionMax;

    public SurfaceRealm(){

    }
    public SurfaceRealm(Surface surface){
        this.positionMax= surface.getPositionMax();
        this.positionMin = surface.getPositionMin();

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
