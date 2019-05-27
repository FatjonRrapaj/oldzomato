package com.example.zomato.client.responses.collections;

import com.google.gson.annotations.SerializedName;

public class Collection{

	@SerializedName("collection_id")
	private int collectionId;

	@SerializedName("res_count")
	private int resCount;

	@SerializedName("image_url")
	private String imageUrl;

	@SerializedName("share_url")
	private String shareUrl;

	@SerializedName("description")
	private String description;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	public int getCollectionId(){
		return collectionId;
	}

	public int getResCount(){
		return resCount;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public String getShareUrl(){
		return shareUrl;
	}

	public String getDescription(){
		return description;
	}

	public String getTitle(){
		return title;
	}

	public String getUrl(){
		return url;
	}
}