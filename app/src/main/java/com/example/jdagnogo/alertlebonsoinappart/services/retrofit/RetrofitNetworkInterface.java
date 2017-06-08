package com.example.jdagnogo.alertlebonsoinappart.services.retrofit;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.BASE_URL;

public interface RetrofitNetworkInterface {
    @GET(BASE_URL)
    Call<ResponseBody> getApparts(@QueryMap(encoded = true) Map<String, String> options);
}