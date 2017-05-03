package com.example.jdagnogo.alertlebonsoinappart.models;

import com.example.jdagnogo.alertlebonsoinappart.enums.City;
import com.example.jdagnogo.alertlebonsoinappart.enums.Meuble;
import com.example.jdagnogo.alertlebonsoinappart.enums.Type;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeff on 02/05/2017.
 */
@Parcel
public class NewSearchView {
    List<City> cities;
    Type type;
    Meuble meuble;
    Rent rent;
    NbRoom nbRoom;
    Surface surface;

    public NewSearchView() {
        this.rent = new Rent();
        this.cities = new ArrayList<>();
        this.nbRoom = new NbRoom();
        this.surface = new Surface();
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
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

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public NbRoom getNbRoom() {
        return nbRoom;
    }

    public void setNbRoom(NbRoom nbRoom) {
        this.nbRoom = nbRoom;
    }

    public Surface getSurface() {
        return surface;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }
}
