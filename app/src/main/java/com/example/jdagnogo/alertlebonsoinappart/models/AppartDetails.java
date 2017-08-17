package com.example.jdagnogo.alertlebonsoinappart.models;

import java.util.ArrayList;
import java.util.List;

public class AppartDetails extends Appart {
    private List<String> imgsUrl;
    private String phone;
    private String nbRoom;
    private String surface;

    public List<String> getImgsUrl() {
        return imgsUrl;
    }

    public AppartDetails(Appart appart) {
        super(appart);
        imgsUrl = new ArrayList<>();
    }

    public void setImgsUrl(List<String> imgsUrl) {
        this.imgsUrl = imgsUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getNbRoom() {
        return nbRoom;
    }

    public void setNbRoom(String nbRoom) {
        this.nbRoom = nbRoom;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }
}

