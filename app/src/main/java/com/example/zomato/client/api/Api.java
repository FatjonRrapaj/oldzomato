package com.example.zomato.client.api;

import com.example.zomato.client.responses.cities.CitiesResponse;
import com.example.zomato.client.responses.collections.CollectionsResponse;
import com.example.zomato.client.responses.establishments.EstablishmentsResponse;
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

    @GET("collections")
    Call<CollectionsResponse> getCollectionsPerCity(@Query(value = "city_id") String cityId);

    @GET("cuisines")
    Call<CollectionsResponse> getCuisinesPerCity(@Query(value = "city_id") String cityId);

    @GET("establishments")
    Call<EstablishmentsResponse> getEstablishmentsPerCity(@Query(value = "city_id") String cityId);


    @GET("search")
    Call<RestaurantsResponse> getRestaurantsPerEstablishment(@Query(value = "entity_id") int entityId,
                                                             @Query(value = "entity_type") String entityType,
                                                             @Query(value = "establishment_type") String establishmentType);

    @GET("search")
    Call<RestaurantsResponse> getRestaurantsPerCuisine(@Query(value = "entity_id") String entityId,
                                                       @Query(value = "entity_type") String entityType,
                                                       @Query(value = "cuisines") String cuisine);

    @GET("search")
    Call<RestaurantsResponse> getRestaurantsPerCollection(@Query(value = "entity_id") String entityId,
                                                          @Query(value = "entity_type") String entityType,
                                                          @Query(value = "collection_id") String collectionId);
}
