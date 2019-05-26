package com.example.zomato.client.responses.cities;

import java.util.List;

import com.example.zomato.client.responses.cities.LocationSuggestionsItem;
import com.google.gson.annotations.SerializedName;

public class CitiesResponse{

	@SerializedName("has_total")
	private int hasTotal;

	@SerializedName("location_suggestions")
	private List<LocationSuggestionsItem> locationSuggestions;

	@SerializedName("has_more")
	private int hasMore;

	@SerializedName("status")
	private String status;

	public int getHasTotal(){
		return hasTotal;
	}

	public List<LocationSuggestionsItem> getLocationSuggestions(){
		return locationSuggestions;
	}

	public int getHasMore(){
		return hasMore;
	}

	public String getStatus(){
		return status;
	}
}