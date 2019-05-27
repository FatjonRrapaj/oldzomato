package com.example.zomato.client.responses.restaurants;

import com.google.gson.annotations.SerializedName;

public class RestaurantsItem{

	@SerializedName("restaurant")
	private Restaurant restaurant;

	public void setRestaurant(Restaurant restaurant){
		this.restaurant = restaurant;
	}

	public Restaurant getRestaurant(){
		return restaurant;
	}
}