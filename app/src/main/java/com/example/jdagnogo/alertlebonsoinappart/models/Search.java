package com.example.jdagnogo.alertlebonsoinappart.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.jdagnogo.alertlebonsoinappart.models.realm.SearchRealm;

import java.util.Date;

public class Search implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeParcelable(this.requestItems, flags);
        dest.writeLong(this.majDate != null ? this.majDate.getTime() : -1);
        dest.writeParcelable(this.lastAppartRealm, flags);
        dest.writeInt(this.jobID);
    }

    protected Search(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.requestItems = in.readParcelable(RequestItems.class.getClassLoader());
        long tmpMajDate = in.readLong();
        this.majDate = tmpMajDate == -1 ? null : new Date(tmpMajDate);
        this.lastAppartRealm = in.readParcelable(Appart.class.getClassLoader());
        this.jobID = in.readInt();
    }

    public static final Creator<Search> CREATOR = new Creator<Search>() {
        @Override
        public Search createFromParcel(Parcel source) {
            return new Search(source);
        }

        @Override
        public Search[] newArray(int size) {
            return new Search[size];
        }
    };
}
