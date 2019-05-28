package com.example.zomato.client.responses.singleRestaurant;

import com.google.gson.annotations.SerializedName;

public class UserRating{

	@SerializedName("aggregate_rating")
	private String aggregateRating;

	@SerializedName("rating_color")
	private String ratingColor;

	@SerializedName("rating_text")
	private String ratingText;

	@SerializedName("votes")
	private String votes;

	public String getAggregateRating(){
		return aggregateRating;
	}

	public String getRatingColor(){
		return ratingColor;
	}

	public String getRatingText(){
		return ratingText;
	}

	public String getVotes(){
		return votes;
	}
}