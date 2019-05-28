package com.example.zomato.client.responses.cuisines;

import com.google.gson.annotations.SerializedName;

public class Cuisine{

	@SerializedName("cuisine_name")
	private String cuisineName;

	@SerializedName("cuisine_id")
	private int cuisineId;

	public String getCuisineName(){
		return cuisineName;
	}

	public int getCuisineId(){
		return cuisineId;
	}
}