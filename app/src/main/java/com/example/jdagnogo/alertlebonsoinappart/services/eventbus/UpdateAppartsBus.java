package com.example.jdagnogo.alertlebonsoinappart.services.eventbus;

import com.example.jdagnogo.alertlebonsoinappart.models.Appart;

import java.util.List;

/**
 * Created by Jeff on 10/06/2017.
 */

public class UpdateAppartsBus {
    private List<Appart> apparts;

    public UpdateAppartsBus(List<Appart> apparts) {
        this.apparts = apparts;
    }

    public List<Appart> getApparts() {
        return apparts;
    }

    public void setApparts(List<Appart> apparts) {
        this.apparts = apparts;
    }
}
