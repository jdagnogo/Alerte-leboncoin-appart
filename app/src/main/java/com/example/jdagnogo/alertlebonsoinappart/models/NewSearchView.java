package com.example.jdagnogo.alertlebonsoinappart.models;

import com.example.jdagnogo.alertlebonsoinappart.enums.City;
import com.example.jdagnogo.alertlebonsoinappart.enums.Meuble;
import com.example.jdagnogo.alertlebonsoinappart.enums.Type;
import com.example.jdagnogo.alertlebonsoinappart.models.swipeItem.NbRoomSwipeItem;
import com.example.jdagnogo.alertlebonsoinappart.models.swipeItem.RentSwipeItem;
import com.example.jdagnogo.alertlebonsoinappart.models.swipeItem.SurfaceSwipeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeff on 02/05/2017.
 */

public class NewSearchView {
    List<City> cities;
    Type type;
    Meuble meuble;
    RentSwipeItem rent;
    NbRoomSwipeItem nbRoom;
    SurfaceSwipeItem surface;

    public NewSearchView() {
        this.rent = new RentSwipeItem();
        this.cities = new ArrayList<>();
        this.nbRoom = new NbRoomSwipeItem();
        this.surface = new SurfaceSwipeItem();
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

    public RentSwipeItem getRent() {
        return rent;
    }

    public void setRent(RentSwipeItem rent) {
        this.rent = rent;
    }

    public NbRoomSwipeItem getNbRoom() {
        return nbRoom;
    }

    public void setNbRoom(NbRoomSwipeItem nbRoom) {
        this.nbRoom = nbRoom;
    }

    public SurfaceSwipeItem getSurface() {
        return surface;
    }

    public void setSurface(SurfaceSwipeItem surface) {
        this.surface = surface;
    }
}
