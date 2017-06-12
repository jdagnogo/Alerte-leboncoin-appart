package com.example.jdagnogo.alertlebonsoinappart.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.jdagnogo.alertlebonsoinappart.enums.City;
import com.example.jdagnogo.alertlebonsoinappart.enums.Meuble;
import com.example.jdagnogo.alertlebonsoinappart.enums.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_CODE_POSTAL;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_MAX;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_MIN;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_SURFACE_MAX;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_SURFACE_MIN;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.MEUBLE_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.LOCATION_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.MORE_CITIES_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.RENT_MAX_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.RENT_MIN_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.ROOM_MAX_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.ROOM_MIN_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.SURFACE_MAX_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.SURFACE_MIN_KEY;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.TYPE_KEY;


public class RequestItems implements Parcelable {
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

    public HashMap<String, String> createHashMap() {
        HashMap<String, String> maps = new HashMap<>();
        //cities
        if (0 == cities.size()) {
            cities.add(City.BORDEAUX_ALL);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < cities.size(); i++) {
            stringBuilder.append(cities.get(i).getCode());
            if (cities.get(i).getCodePostal() != DEFAULT_CODE_POSTAL) {
                stringBuilder.append(cities.get(i).getCodePostal());
            }
            stringBuilder.append(MORE_CITIES_KEY);
        }
        String c = stringBuilder.toString();
         c.substring(c.length()-3);
        maps.put(LOCATION_KEY,c);

        //meuble
        if (!meuble.getMeubleName().equals(Meuble.MEUBLE_DEFAULT)) {
            maps.put(MEUBLE_KEY, String.valueOf(meuble.getValue()));
        }
        // Type
        if (!type.getTypeName().equals(Type.APPARTEMENT_DEFAULT)) {
            maps.put(TYPE_KEY, String.valueOf(type.getValue()));
        }
        // Rent
        //min
        if (!rent.getPositionMin().equals(DEFAULT_MIN)) {
            maps.put(RENT_MIN_KEY, rent.getPositionMin());
        }
        //max
        if (!rent.getPositionMax().equals(DEFAULT_MAX)) {
            maps.put(RENT_MAX_KEY, rent.getPositionMax());
        }
        // Nb room
        //min
        if (!nbRoom.getPositionMin().equals(DEFAULT_MIN)) {
            maps.put(ROOM_MIN_KEY, nbRoom.getPositionMin());
        }
        //max
        if (!nbRoom.getPositionMax().equals(DEFAULT_MAX)) {
            maps.put(ROOM_MAX_KEY, nbRoom.getPositionMax());
        }
        // surface
        //min
        if (!surface.getPositionMin().equals(DEFAULT_SURFACE_MIN)) {
            maps.put(SURFACE_MIN_KEY, surface.getPositionMin());
        }
        //max
        if (!surface.getPositionMax().equals(DEFAULT_SURFACE_MAX)) {
            maps.put(SURFACE_MAX_KEY, surface.getPositionMax());
        }
        return maps;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.cities);
        dest.writeParcelable(this.rent, flags);
        dest.writeParcelable(this.surface, flags);
        dest.writeParcelable(this.nbRoom, flags);
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
        dest.writeInt(this.meuble == null ? -1 : this.meuble.ordinal());
        dest.writeString(this.keyWord);
        dest.writeByte(this.urgente ? (byte) 1 : (byte) 0);
    }

    protected RequestItems(Parcel in) {
        this.cities = new ArrayList<City>();
        in.readList(this.cities, City.class.getClassLoader());
        this.rent = in.readParcelable(Rent.class.getClassLoader());
        this.surface = in.readParcelable(Surface.class.getClassLoader());
        this.nbRoom = in.readParcelable(NbRoom.class.getClassLoader());
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : Type.values()[tmpType];
        int tmpMeuble = in.readInt();
        this.meuble = tmpMeuble == -1 ? null : Meuble.values()[tmpMeuble];
        this.keyWord = in.readString();
        this.urgente = in.readByte() != 0;
    }

    public static final Creator<RequestItems> CREATOR = new Creator<RequestItems>() {
        @Override
        public RequestItems createFromParcel(Parcel source) {
            return new RequestItems(source);
        }

        @Override
        public RequestItems[] newArray(int size) {
            return new RequestItems[size];
        }
    };
}
