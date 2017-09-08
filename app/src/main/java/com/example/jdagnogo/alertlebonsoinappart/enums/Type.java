package com.example.jdagnogo.alertlebonsoinappart.enums;

public enum Type {
    MAISON("Maison",1),
    APPARTEMENT("Appartemen",2),
    APPARTEMENT_DEFAULT("les deux",0)
    ;

    String typeName;
    int value;

    Type(String typeName,int value){
        this.typeName =typeName;
        this.value= value;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getValue() {
        return value;
    }
}
