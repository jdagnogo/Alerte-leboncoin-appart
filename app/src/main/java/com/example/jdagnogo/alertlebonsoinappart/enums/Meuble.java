package com.example.jdagnogo.alertlebonsoinappart.enums;

public enum Meuble {

    MEUBLE("Meublé",1),
    MEUBLE_DEFAULT("Meublé",0),
    NON_MEUBLE("Non meublé", 2);

    String meubleName;
    int value;

    Meuble(String meubleName, int value){
        this.meubleName = meubleName;
        this.value = value;
    }

    public String getMeubleName() {
        return meubleName;
    }

    public int getValue() {
        return value;
    }
}
