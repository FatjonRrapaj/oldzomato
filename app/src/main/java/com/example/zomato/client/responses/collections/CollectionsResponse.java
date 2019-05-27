package com.example.zomato.client.responses.collections;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CollectionsResponse{

	@SerializedName("has_total")
	private int hasTotal;

	@SerializedName("collections")
	private List<CollectionsItem> collections;

	@SerializedName("share_url")
	private String shareUrl;

	@SerializedName("has_more")
	private int hasMore;

	@SerializedName("display_text")
	private String displayText;

	public int getHasTotal(){
		return hasTotal;
	}

	public List<CollectionsItem> getCollections(){
		return collections;
	}

	public String getShareUrl(){
		return shareUrl;
	}

	public int getHasMore(){
		return hasMore;
	}

	public String getDisplayText(){
		return displayText;
	}
}