package com.example.jdagnogo.alertlebonsoinappart.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.jdagnogo.alertlebonsoinappart.enums.City;
import com.example.jdagnogo.alertlebonsoinappart.enums.Meuble;
import com.example.jdagnogo.alertlebonsoinappart.enums.Type;

import java.util.ArrayList;
import java.util.List;


public class RequestItems implements Parcelable{
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
