package com.example.jdagnogo.alertlebonsoinappart.enums.realm;

import com.example.jdagnogo.alertlebonsoinappart.enums.Type;

import io.realm.RealmObject;

public class TypeRealm extends RealmObject{
    private String enumDescription;

    public void saveEnum(Type val) {
        this.enumDescription = val.toString();
    }

    public Type getEnum() {
        return (enumDescription != null) ? Type.valueOf(enumDescription) : null;
    }

}
