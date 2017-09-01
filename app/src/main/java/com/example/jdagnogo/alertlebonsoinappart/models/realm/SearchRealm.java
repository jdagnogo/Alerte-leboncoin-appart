package com.example.jdagnogo.alertlebonsoinappart.models.realm;

import com.example.jdagnogo.alertlebonsoinappart.models.Search;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SearchRealm extends RealmObject {
    @PrimaryKey
    private int id;
    private RequestItemsRealm requestItems;
    private Date majDate;
    private AppartRealm lastAppartRealm;
    private int jobID;

    public SearchRealm() {
    }

    public Search getSearch() {
        Search search = new Search();
        search.setId(id);
        search.setJobID(jobID);
        search.setLastAppartRealm(lastAppartRealm.getAppart());
        search.setMajDate(majDate);
        search.setRequestItems(requestItems.getRequestItem());
        return search;
    }

    public SearchRealm(int id, RequestItemsRealm requestItems, Date majDate, AppartRealm lastAppartRealm) {
        this.id = id;
        this.requestItems = requestItems;
        this.majDate = majDate;
        this.lastAppartRealm = lastAppartRealm;
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

    public SearchRealm( Date majDate, AppartRealm lastAppartRealm, RequestItemsRealm requestItemsRealm) {

        this.requestItems = requestItemsRealm;
        this.majDate = majDate;
        this.lastAppartRealm = lastAppartRealm;
    }



    public Date getMajDate() {
        return majDate;
    }

    public void setMajDate(Date majDate) {
        this.majDate = majDate;
    }

    public AppartRealm getLastAppartRealm() {
        return lastAppartRealm;
    }

    public void setLastAppartRealm(AppartRealm lastAppartRealm) {
        this.lastAppartRealm = lastAppartRealm;
    }
}
