package com.example.jdagnogo.alertlebonsoinappart.services;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
public interface RetrofitNetworkInterface {
    @FormUrlEncoded
    @GET("/locations/offres/ile_de_france/occasions/?th=1&parrot=0&")
    Call<String> allappart();
}