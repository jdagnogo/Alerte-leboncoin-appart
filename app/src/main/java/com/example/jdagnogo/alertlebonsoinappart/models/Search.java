package com.example.jdagnogo.alertlebonsoinappart.models;

import com.example.jdagnogo.alertlebonsoinappart.models.realm.SearchRealm;

import java.util.Date;

public class Search {

    private int id;
    private String title;
    private RequestItems requestItems;
    private Date majDate;
    private Appart lastAppartRealm;
    private int jobID ;

    public Search() {
    }


    public Search(SearchRealm searchRealm) {
        this.id = searchRealm.getId();
        this.title = searchRealm.getTitle();
        this.requestItems = searchRealm.getRequestItemsRealm().getRequestItem();
        this.majDate =searchRealm.getMajDate();
        this.lastAppartRealm = new Appart(searchRealm.getLastAppartRealm());
        this.jobID =searchRealm.getJobID();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RequestItems getRequestItems() {
        return requestItems;
    }

    public void setRequestItems(RequestItems requestItems) {
        this.requestItems = requestItems;
    }

    public Date getMajDate() {
        return majDate;
    }

    public void setMajDate(Date majDate) {
        this.majDate = majDate;
    }

    public Appart getLastAppartRealm() {
        return lastAppartRealm;
    }

    public void setLastAppartRealm(Appart lastAppartRealm) {
        this.lastAppartRealm = lastAppartRealm;
    }


    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }
}
