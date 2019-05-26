package com.example.zomato.client.api;

import com.example.zomato.client.responses.cities.CitiesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("cities")
    Call<CitiesResponse> getCitiesPerState(@Query(value="city_ids") String cityIds);


}
