package com.example.jdagnogo.alertlebonsoinappart.models;

import com.example.jdagnogo.alertlebonsoinappart.models.realm.RequestItemsRealm;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Search extends RealmObject {
    @PrimaryKey
    private int id;
    private String title;
    private RequestItemsRealm requestItems;
    private Date majDate;
    private Appart lastAppart;
    private int jobID ;

    public Search() {
    }

    public Search(int id, String title, RequestItemsRealm requestItems, Date majDate, Appart lastAppart) {
        this.id = id;
        this.title = title;
        this.requestItems = requestItems;
        this.majDate = majDate;
        this.lastAppart = lastAppart;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RequestItemsRealm getRequestItemsRealm() {
        return requestItems;
    }

    public void setRequestItems(RequestItemsRealm requestItems) {
        this.requestItems = requestItems;
    }

    public Search(String title, Date majDate, Appart lastAppart, RequestItemsRealm requestItemsRealm) {
        this.title = title;
        this.requestItems = requestItemsRealm;
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
