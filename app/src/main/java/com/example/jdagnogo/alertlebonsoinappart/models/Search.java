package com.example.jdagnogo.alertlebonsoinappart.models;

import java.util.Date;
import java.util.HashMap;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Search extends RealmObject {

    private String title;

    private Date majDate;
    private Appart lastAppart;

    public Search() {
    }

    public Search(String title, Date majDate, Appart lastAppart) {
        this.title = title;
        this.majDate = majDate;
        this.lastAppart = lastAppart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public Date getMajDate() {
        return majDate;
    }

    public void setMajDate(Date majDate) {
        this.majDate = majDate;
    }

    public Appart getLastAppart() {
        return lastAppart;
    }

    public void setLastAppart(Appart lastAppart) {
        this.lastAppart = lastAppart;
    }
}
