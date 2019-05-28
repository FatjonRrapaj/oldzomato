package com.example.zomato.client.responses.singleRestaurant;

import com.google.gson.annotations.SerializedName;

public class R{

	@SerializedName("res_id")
	private int resId;

	public int getResId(){
		return resId;
	}
}