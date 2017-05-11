package com.example.jdagnogo.alertlebonsoinappart.services.retrofit;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitNetworkInterface {
    @GET("/locations/offres/ile_de_france/occasions/?th=1&parrot=0&")
    Call<ResponseBody> allAppart(@Query("query") String query);
}