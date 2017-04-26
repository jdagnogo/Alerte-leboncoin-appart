package com.example.jdagnogo.alertlebonsoinappart.enums;

public enum Ville {

    BORDEAUX_CENTRE("Bordeaux centre",  33000),
    TALENCE("Talence",  33400),
    BEGLE("Bègles",  33130),
    MERIGNAC("Mérignac",  33700),
    PESSAC("Pessac",  33600),
    BORDEAUX_ALL("Bordeaux (Toute la ville)",  0), // 0 means don t put it in the request
    TOULOUSE_ALL("Toulouse (Toute la ville)",  0),
    ;

    public String getCityName() {
        return cityName;
    }

    public int getCodePostal() {
        return codePostal;
    }

    String cityName;
    int codePostal;

    private Ville(String cityName, int codePostal){
        this.cityName =cityName;
        this.codePostal =codePostal;
    }
}
