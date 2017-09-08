package com.example.jdagnogo.alertlebonsoinappart.enums;


import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.DEFAULT_CODE_POSTAL;

public enum City {

    BORDEAUX_33000("Bordeaux","Bordeaux ",  "33000"),
    BORDEAUX_33200("Bordeaux ( 33200 )","Bordeaux ",  "33200"),
    BORDEAUX_33300("Bordeaux ( 33300 )","Bordeaux ",  "33300"),
    BORDEAUX_33800("Bordeaux ( 33800 )","Bordeaux ",  "33800"),
    BORDEAUX_33100("Bordeaux ( 33100 )","Bordeaux ",  "33100"),
    TALENCE_33400("Talence", "Talence ", "33400"),
    BRUGES_33520("Bruges", "Bruges ", "33520"),
    LORMONT_33310("Lormont", "Lormont ", "33310"),
    FLORAC_33270("Florac", "Florac ", "33270"),
    EYSINES_33320("Eysines", "Eysines ", "33320"),

    BLANQUEFORT_33290("Blanquefort", "Blanquefort ", "33290"),
    CENON_33150("Cenon", "Cenon ", "33150"),
    //LEHAILLAN_33185("LeHaillan", "Le%20Haillan ", "33185"),

    //MERIGNAC_33700("Mérignac", "M%E9rignac ", "33700"),
    PESSAC_33600("Pessac", "Pessac ", "33600"),
    BORDEAUX_0("Bordeaux (Toute la ville) ","Bordeaux",  DEFAULT_CODE_POSTAL), // 0 means don t put it in the request
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

