package com.example.zomato.client.responses.cuisines;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CuisinesResponse{

	@SerializedName("cuisines")
	private List<CuisinesItem> cuisines;

	public List<CuisinesItem> getCuisines(){
		return cuisines;
	}
}