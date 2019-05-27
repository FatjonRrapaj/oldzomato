package com.example.zomato.client.responses.establishments;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class EstablishmentsResponse{

	@SerializedName("establishments")
	private List<EstablishmentsItem> establishments;

	public List<EstablishmentsItem> getEstablishments(){
		return establishments;
	}
}