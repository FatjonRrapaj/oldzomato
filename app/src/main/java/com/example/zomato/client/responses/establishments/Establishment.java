package com.example.zomato.client.responses.establishments;

import com.google.gson.annotations.SerializedName;

public class Establishment{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}