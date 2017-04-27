package com.example.jdagnogo.alertlebonsoinappart.models;

import com.example.jdagnogo.alertlebonsoinappart.enums.City;
import com.example.jdagnogo.alertlebonsoinappart.enums.Meuble;
import com.example.jdagnogo.alertlebonsoinappart.enums.Type;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_RENT_MAX;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_RENT_MIN;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_ROOM_MAX;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_ROOM_MIN;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_SURFACE_MAX;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_SURFACE_MIN;

@Parcel
public class RequestItems {
    List<City> cities;
    int rentMin;
    int rentMax;
    int surfaceMin;
    int surfaceMax;
    int roomMin;
    int roomMax;
    Type type;
    Meuble meuble;
    String keyWord;
    boolean urgente;


    public RequestItems() {
        this.cities = new ArrayList<>();
        this.meuble = Meuble.MEUBLE_DEFAULT;
        this.type = Type.APPARTEMENT_DEFAULT;
        this.rentMin = DEFAULT_RENT_MIN;
        this.rentMax = DEFAULT_RENT_MAX;
        this.surfaceMin = DEFAULT_SURFACE_MIN;
        this.surfaceMax = DEFAULT_SURFACE_MAX;
        this.roomMin = DEFAULT_ROOM_MIN;
        this.roomMax =DEFAULT_ROOM_MAX;
        this.keyWord = "";
    }

    @Override
    public String toString() {
        return "RequestItems{" +
                "cities=" + cities +
                ", rentMin=" + rentMin +
                ", rentMax=" + rentMax +
                ", surfaceMin=" + surfaceMin +
                ", surfaceMax=" + surfaceMax +
                ", roomMin=" + roomMin +
                ", roomMax=" + roomMax +
                ", type=" + type +
                ", meuble=" + meuble +
                ", keyWord='" + keyWord + '\'' +
                ", urgente=" + urgente +
                '}';
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public int getRentMin() {
        return rentMin;
    }

    public void setRentMin(int rentMin) {
        this.rentMin = rentMin;
    }

    public int getRentMax() {
        return rentMax;
    }

    public void setRentMax(int rentMax) {
        this.rentMax = rentMax;
    }

    public int getSurfaceMin() {
        return surfaceMin;
    }

    public void setSurfaceMin(int surfaceMin) {
        this.surfaceMin = surfaceMin;
    }

    public int getSurfaceMax() {
        return surfaceMax;
    }

    public void setSurfaceMax(int surfaceMax) {
        this.surfaceMax = surfaceMax;
    }

    public int getRoomMin() {
        return roomMin;
    }

    public void setRoomMin(int roomMin) {
        this.roomMin = roomMin;
    }

    public int getRoomMax() {
        return roomMax;
    }

    public void setRoomMax(int roomMax) {
        this.roomMax = roomMax;
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
