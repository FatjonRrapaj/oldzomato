package com.example.zomato.client.api;

import com.example.zomato.client.responses.cities.CitiesResponse;
import com.example.zomato.client.responses.restaurants.RestaurantsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("cities")
    Call<CitiesResponse> getCitiesPerState(@Query(value = "city_ids") String cityIds);

    @GET("search")
    Call<RestaurantsResponse> getRestaurantsPerEntity(@Query(value = "entity_id") int entityId,
                                                      @Query(value = "entity_type") String entityType,
                                                      @Query(value = "count") int count);

    @GET("search")
    Call<RestaurantsResponse> getRestaurantsPerCuisines(@Query(value = "cuisines") String cuisines,
                                                        @Query(value = "count") int count);

    @GET("search")
    Call<RestaurantsResponse> getRestaurantsPerCategory(@Query(value = "category") String categories,
                                                        @Query(value = "count") int count);

}
