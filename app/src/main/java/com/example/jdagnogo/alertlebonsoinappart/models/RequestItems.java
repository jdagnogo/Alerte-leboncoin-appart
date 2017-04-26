package com.example.jdagnogo.alertlebonsoinappart.models;

import com.example.jdagnogo.alertlebonsoinappart.enums.City;
import com.example.jdagnogo.alertlebonsoinappart.enums.Meuble;
import com.example.jdagnogo.alertlebonsoinappart.enums.Type;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class RequestItems {
    List<City> cities;
    int loyerMin;
    int loyerMax;
    int surfaceMin;
    int surfaceMax;
    int nbPieceMin;
    int nbPieceMax;
    Type type;
    Meuble meuble;
    String motCle;
    boolean urgente;


    public RequestItems() {
    }

    @Override
    public String toString() {
        return "RequestItems{" +
                "cities=" + cities +
                ", loyerMin=" + loyerMin +
                ", loyerMax=" + loyerMax +
                ", surfaceMin=" + surfaceMin +
                ", surfaceMax=" + surfaceMax +
                ", nbPieceMin=" + nbPieceMin +
                ", nbPieceMax=" + nbPieceMax +
                ", type=" + type +
                ", meuble=" + meuble +
                ", motCle='" + motCle + '\'' +
                ", urgente=" + urgente +
                '}';
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public int getLoyerMin() {
        return loyerMin;
    }

    public void setLoyerMin(int loyerMin) {
        this.loyerMin = loyerMin;
    }

    public int getLoyerMax() {
        return loyerMax;
    }

    public void setLoyerMax(int loyerMax) {
        this.loyerMax = loyerMax;
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

    public int getNbPieceMin() {
        return nbPieceMin;
    }

    public void setNbPieceMin(int nbPieceMin) {
        this.nbPieceMin = nbPieceMin;
    }

    public int getNbPieceMax() {
        return nbPieceMax;
    }

    public void setNbPieceMax(int nbPieceMax) {
        this.nbPieceMax = nbPieceMax;
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

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }
}
