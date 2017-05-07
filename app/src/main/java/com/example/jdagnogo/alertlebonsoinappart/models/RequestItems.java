package com.example.jdagnogo.alertlebonsoinappart.models;

import com.example.jdagnogo.alertlebonsoinappart.enums.City;
import com.example.jdagnogo.alertlebonsoinappart.enums.Meuble;
import com.example.jdagnogo.alertlebonsoinappart.enums.Type;

import java.util.ArrayList;
import java.util.List;


public class RequestItems {
    List<City> cities;
    Rent rent;
    Surface surface;
    NbRoom nbRoom;
    Type type;
    Meuble meuble;
    String keyWord;
    boolean urgente;


    public RequestItems() {
        this.cities = new ArrayList<>();
        this.meuble = Meuble.MEUBLE_DEFAULT;
        this.type = Type.APPARTEMENT_DEFAULT;
        this.rent = new Rent();
        this.surface = new Surface();
        this.nbRoom = new NbRoom();
        this.keyWord = "";
    }


    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Surface getSurface() {
        return surface;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }

    public NbRoom getNbRoom() {
        return nbRoom;
    }

    public void setNbRoom(NbRoom nbRoom) {
        this.nbRoom = nbRoom;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Meuble getMeuble() {
        return meuble;
    }

    public void setMeuble(Meuble meuble) {
        this.meuble = meuble;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }
}
