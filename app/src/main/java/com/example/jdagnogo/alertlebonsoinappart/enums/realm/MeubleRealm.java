package com.example.jdagnogo.alertlebonsoinappart.enums.realm;

import com.example.jdagnogo.alertlebonsoinappart.enums.Meuble;

import io.realm.RealmObject;

public class MeubleRealm extends RealmObject {

    private String enumDescription;

    public void saveEnum(Meuble val) {
        this.enumDescription = val.toString();
    }

    public Meuble getEnum() {
        return (enumDescription != null) ? Meuble.valueOf(enumDescription) : null;
    }


}
