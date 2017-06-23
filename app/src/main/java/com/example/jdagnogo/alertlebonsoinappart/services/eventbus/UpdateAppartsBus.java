package com.example.jdagnogo.alertlebonsoinappart.services.eventbus;

import com.example.jdagnogo.alertlebonsoinappart.models.realm.AppartRealm;

import java.util.List;

/**
 * Created by Jeff on 10/06/2017.
 */

public class UpdateAppartsBus {
    private List<AppartRealm> appartRealms;

    public UpdateAppartsBus(List<AppartRealm> appartRealms) {
        this.appartRealms = appartRealms;
    }

    public List<AppartRealm> getAppartRealms() {
        return appartRealms;
    }

    public void setAppartRealms(List<AppartRealm> appartRealms) {
        this.appartRealms = appartRealms;
    }
}
