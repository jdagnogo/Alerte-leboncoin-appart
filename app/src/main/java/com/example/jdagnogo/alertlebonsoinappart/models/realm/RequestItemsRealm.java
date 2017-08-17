package com.example.jdagnogo.alertlebonsoinappart.models.realm;

import com.example.jdagnogo.alertlebonsoinappart.enums.City;
import com.example.jdagnogo.alertlebonsoinappart.enums.realm.CityRealm;
import com.example.jdagnogo.alertlebonsoinappart.enums.realm.MeubleRealm;
import com.example.jdagnogo.alertlebonsoinappart.enums.realm.TypeRealm;
import com.example.jdagnogo.alertlebonsoinappart.models.NbRoom;
import com.example.jdagnogo.alertlebonsoinappart.models.Rent;
import com.example.jdagnogo.alertlebonsoinappart.models.RequestItems;
import com.example.jdagnogo.alertlebonsoinappart.models.Surface;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class RequestItemsRealm extends RealmObject {

    RealmList<CityRealm> cities;
    RentRealm rent;
    SurfaceRealm surface;
    NbRoomRealm nbRoom;
    TypeRealm type;
    MeubleRealm meuble;
    String keyWord;
    boolean urgente;

    public RequestItemsRealm() {

    }

    public RequestItems getRequestItem() {
        RequestItems requestItems = new RequestItems();
        requestItems.setType(type.getEnum());
        requestItems.setMeuble(meuble.getEnum());

        NbRoom nbRoom = new NbRoom(this.nbRoom);
        requestItems.setNbRoom(nbRoom);

        Surface surface = new Surface(this.surface);
        requestItems.setSurface(surface);

        Rent rent = new Rent(this.rent);
        requestItems.setRent(rent);

        List<City> cities = new ArrayList<>();
        for (int i = 0; i < this.cities.size(); i++) {
            cities.add(this.cities.get(i).getEnum());
        }
        requestItems.setCities(cities);
        requestItems.setKeyWord(keyWord);

        return requestItems;
    }

    public RequestItemsRealm(RequestItems requestItems) {
        cities = new RealmList<>();
        for (int i = 0; i < requestItems.getCities().size(); i++) {
            CityRealm cityRealm = new CityRealm();
            cityRealm.saveEnum(requestItems.getCities().get(i));
            cities.add(cityRealm);
        }
        rent = new RentRealm(requestItems.getRent());
        surface = new SurfaceRealm(requestItems.getSurface());
        nbRoom = new NbRoomRealm(requestItems.getNbRoom());
        type = new TypeRealm();
        type.saveEnum(requestItems.getType());
        meuble = new MeubleRealm();
        meuble.saveEnum(requestItems.getMeuble());
        keyWord = requestItems.getKeyWord();
    }
}
