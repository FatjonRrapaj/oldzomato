package com.example.zomato.client.responses.restaurants;

import com.google.gson.annotations.SerializedName;

public class R{

	@SerializedName("res_id")
	private int resId;

	public void setResId(int resId){
		this.resId = resId;
	}

	public int getResId(){
		return resId;
	}
}