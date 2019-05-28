package com.example.zomato.client.api;

import androidx.cardview.widget.CardView;

import com.example.zomato.client.responses.cities.CitiesResponse;
import com.example.zomato.client.responses.collections.CollectionsResponse;
import com.example.zomato.client.responses.restaurants.RestaurantsResponse;
import com.example.zomato.client.responses.singleRestaurant.SingleRestaurantResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("cities")
    Call<CitiesResponse> getCitiesPerState(@Query(value = "city_ids") String cityIds);

    @GET("search")
    Call<RestaurantsResponse> getRestaurantsPerEntity(@Query(value = "entity_id") String entityId,
                                                      @Query(value = "entity_type") String entityType,
                                                      @Query(value = "count") int count);

    @GET("collections")
    Call<CollectionsResponse> getCollectionsPerCity(@Query(value = "city_id") String cityId);

    @GET("search")
    Call<RestaurantsResponse> getRestaurantsPerCollection(@Query(value = "entity_id") String entityId,
                                                          @Query(value = "entity_type") String entityType,
                                                          @Query(value = "collection_id") String collectionId);
    @GET("restaurant")
    Call<SingleRestaurantResponse> getRestaurant(@Query(value = "res_id") String resId);
}
