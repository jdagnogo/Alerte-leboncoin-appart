package com.example.jdagnogo.alertlebonsoinappart.models;

import android.os.Parcel;
import android.os.Parcelable;

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

public class NewSearchView implements Parcelable{
    List<City> cities;
    Type type;
    Meuble meuble;
    RentSwipeItem rent;
    NbRoomSwipeItem nbRoom;
    SurfaceSwipeItem surface;
    String name;
    String query;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.cities);
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
        dest.writeInt(this.meuble == null ? -1 : this.meuble.ordinal());
        dest.writeParcelable(this.rent, flags);
        dest.writeParcelable(this.nbRoom, flags);
        dest.writeParcelable(this.surface, flags);
        dest.writeString(this.name);
        dest.writeString(this.query);
    }

    protected NewSearchView(Parcel in) {
        this.cities = new ArrayList<City>();
        in.readList(this.cities, City.class.getClassLoader());
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : Type.values()[tmpType];
        int tmpMeuble = in.readInt();
        this.meuble = tmpMeuble == -1 ? null : Meuble.values()[tmpMeuble];
        this.rent = in.readParcelable(RentSwipeItem.class.getClassLoader());
        this.nbRoom = in.readParcelable(NbRoomSwipeItem.class.getClassLoader());
        this.surface = in.readParcelable(SurfaceSwipeItem.class.getClassLoader());
        this.name = in.readString();
        this.query = in.readString();
    }

    public static final Creator<NewSearchView> CREATOR = new Creator<NewSearchView>() {
        @Override
        public NewSearchView createFromParcel(Parcel source) {
            return new NewSearchView(source);
        }

        @Override
        public NewSearchView[] newArray(int size) {
            return new NewSearchView[size];
        }
    };
}
