package com.example.jdagnogo.alertlebonsoinappart.models.realm;

import com.example.jdagnogo.alertlebonsoinappart.models.Appart;

import io.realm.RealmObject;

/**
 * Created by Jeff on 09/06/2017.
 */

public class AppartRealm extends RealmObject {
    private String image;
    private String price;
    private String title;
    private String date;
    private boolean isPro;

    public AppartRealm(){

    }
    public AppartRealm(String image, String price, String title, String date, boolean isPro) {
        this.image = image;
        this.price = price;
        this.title = title;
        this.date = date;
        this.isPro = isPro;
    }
    public AppartRealm(Appart appart){
        this.image = appart.getImage();
        this.price = appart.getPrice();
        this.title = appart.getTitle();
        this.date = appart.getDate();
        this.isPro = appart.isPro();
    }

    public Appart getAppart(){
        Appart appart = new Appart();
        appart.setDate(date);
        appart.setImage(image);
        appart.setPrice(price);
        appart.setPro(isPro);
        appart.setTitle(title);
        return appart;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPro() {
        return isPro;
    }

    public void setPro(boolean pro) {
        isPro = pro;
    }
}
