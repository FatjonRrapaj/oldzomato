package com.example.zomato.client.responses.cities;

import com.google.gson.annotations.SerializedName;

public class LocationSuggestionsItem{

	@SerializedName("should_experiment_with")
	private int shouldExperimentWith;

	@SerializedName("has_new_ad_format")
	private int hasNewAdFormat;

	@SerializedName("is_state")
	private int isState;

	@SerializedName("state_name")
	private String stateName;

	@SerializedName("name")
	private String name;

	@SerializedName("country_name")
	private String countryName;

	@SerializedName("country_flag_url")
	private String countryFlagUrl;

	@SerializedName("id")
	private int id;

	@SerializedName("state_id")
	private int stateId;

	@SerializedName("state_code")
	private String stateCode;

	@SerializedName("country_id")
	private int countryId;

	@SerializedName("discovery_enabled")
	private int discoveryEnabled;

	public int getShouldExperimentWith(){
		return shouldExperimentWith;
	}

	public int getHasNewAdFormat(){
		return hasNewAdFormat;
	}

	public int getIsState(){
		return isState;
	}

	public String getStateName(){
		return stateName;
	}

	public String getName(){
		return name;
	}

	public String getCountryName(){
		return countryName;
	}

	public String getCountryFlagUrl(){
		return countryFlagUrl;
	}

	public int getId(){
		return id;
	}

	public int getStateId(){
		return stateId;
	}

	public String getStateCode(){
		return stateCode;
	}

	public int getCountryId(){
		return countryId;
	}

	public int getDiscoveryEnabled(){
		return discoveryEnabled;
	}
}