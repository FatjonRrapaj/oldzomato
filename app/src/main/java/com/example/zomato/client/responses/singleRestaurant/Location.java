package com.example.zomato.client.responses.singleRestaurant;

import com.google.gson.annotations.SerializedName;

public class Location{

	@SerializedName("zipcode")
	private String zipcode;

	@SerializedName("address")
	private String address;

	@SerializedName("city")
	private String city;

	@SerializedName("locality_verbose")
	private String localityVerbose;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("locality")
	private String locality;

	@SerializedName("country_id")
	private int countryId;

	@SerializedName("city_id")
	private int cityId;

	@SerializedName("longitude")
	private String longitude;

	public String getZipcode(){
		return zipcode;
	}

	public String getAddress(){
		return address;
	}

	public String getCity(){
		return city;
	}

	public String getLocalityVerbose(){
		return localityVerbose;
	}

	public String getLatitude(){
		return latitude;
	}

	public String getLocality(){
		return locality;
	}

	public int getCountryId(){
		return countryId;
	}

	public int getCityId(){
		return cityId;
	}

	public String getLongitude(){
		return longitude;
	}
}