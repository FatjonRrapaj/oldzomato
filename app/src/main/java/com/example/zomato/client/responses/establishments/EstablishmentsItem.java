package com.example.zomato.client.responses.establishments;

import com.google.gson.annotations.SerializedName;

public class EstablishmentsItem{

	@SerializedName("establishment")
	private Establishment establishment;

	public Establishment getEstablishment(){
		return establishment;
	}
}