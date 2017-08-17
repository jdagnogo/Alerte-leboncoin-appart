package com.example.jdagnogo.alertlebonsoinappart.enums;


import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_CODE_POSTAL;

public enum City {

    BORDEAUX_CENTRE("Bordeaux centre","Bordeaux ",  "33000"),
    TALENCE("Talence", "Talence ", "33400"),
    //BEGLE("Bègles",  33130),
    MERIGNAC("Mérignac", "Mérignac ", "33700"),
    PESSAC("Pessac", "Pessac ", "33600"),
    BORDEAUX_ALL("Bordeaux (Toute la ville) ","Bordeaux",  DEFAULT_CODE_POSTAL), // 0 means don t put it in the request
    //TOULOUSE_ALL("Toulouse (Toute la ville)",  0),
    ;

    public String getCityName() {
        return cityName;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getCode(){
        return code;
    }
    String cityName;
    String codePostal;
    String code;


    City(String cityName, String code, String codePostal){
        this.cityName =cityName;
        this.codePostal =codePostal;
        this.code = code;
    }
}

