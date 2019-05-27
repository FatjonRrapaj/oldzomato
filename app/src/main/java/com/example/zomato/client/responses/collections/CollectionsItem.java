package com.example.zomato.client.responses.collections;

import com.google.gson.annotations.SerializedName;

public class CollectionsItem{

	@SerializedName("collection")
	private Collection collection;

	public Collection getCollection(){
		return collection;
	}
}