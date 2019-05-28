package com.example.zomato.client.responses.cuisines;

import com.google.gson.annotations.SerializedName;

public class CuisinesItem{

	@SerializedName("cuisine")
	private Cuisine cuisine;

	public Cuisine getCuisine(){
		return cuisine;
	}
}