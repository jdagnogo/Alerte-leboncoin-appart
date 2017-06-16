package com.example.jdagnogo.alertlebonsoinappart.models.realm;

import com.example.jdagnogo.alertlebonsoinappart.models.NbRoom;

import io.realm.RealmObject;

public class NbRoomRealm extends RealmObject {
    private String positionMin;
    private String positionMax;

    public NbRoomRealm(){

    }
    public NbRoomRealm(NbRoom nbRoom){
        this.positionMax= nbRoom.getPositionMax();
        this.positionMin = nbRoom.getPositionMin();

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
