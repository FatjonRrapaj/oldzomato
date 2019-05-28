package com.example.zomato.client.responses.singleRestaurant;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SingleRestaurantResponse{

	@SerializedName("include_bogo_offers")
	private boolean includeBogoOffers;

	@SerializedName("has_online_delivery")
	private int hasOnlineDelivery;

	@SerializedName("has_table_booking")
	private int hasTableBooking;

	@SerializedName("thumb")
	private String thumb;

	@SerializedName("average_cost_for_two")
	private int averageCostForTwo;

	@SerializedName("menu_url")
	private String menuUrl;

	@SerializedName("price_range")
	private int priceRange;

	@SerializedName("switch_to_order_menu")
	private int switchToOrderMenu;

	@SerializedName("R")
	private R R;

	@SerializedName("is_table_reservation_supported")
	private int isTableReservationSupported;

	@SerializedName("currency")
	private String currency;

	@SerializedName("opentable_support")
	private int opentableSupport;

	@SerializedName("id")
	private String id;

	@SerializedName("user_rating")
	private UserRating userRating;

	@SerializedName("offers")
	private List<Object> offers;

	@SerializedName("apikey")
	private String apikey;

	@SerializedName("is_delivering_now")
	private int isDeliveringNow;

	@SerializedName("deeplink")
	private String deeplink;

	@SerializedName("is_zomato_book_res")
	private int isZomatoBookRes;

	@SerializedName("featured_image")
	private String featuredImage;

	@SerializedName("url")
	private String url;

	@SerializedName("cuisines")
	private String cuisines;

	@SerializedName("events_url")
	private String eventsUrl;

	@SerializedName("name")
	private String name;

	@SerializedName("location")
	private Location location;

	@SerializedName("book_again_url")
	private String bookAgainUrl;

	@SerializedName("is_book_form_web_view")
	private int isBookFormWebView;

	@SerializedName("book_form_web_view_url")
	private String bookFormWebViewUrl;

	@SerializedName("mezzo_provider")
	private String mezzoProvider;

	@SerializedName("photos_url")
	private String photosUrl;

	public boolean isIncludeBogoOffers(){
		return includeBogoOffers;
	}

	public int getHasOnlineDelivery(){
		return hasOnlineDelivery;
	}

	public int getHasTableBooking(){
		return hasTableBooking;
	}

	public String getThumb(){
		return thumb;
	}

	public int getAverageCostForTwo(){
		return averageCostForTwo;
	}

	public String getMenuUrl(){
		return menuUrl;
	}

	public int getPriceRange(){
		return priceRange;
	}

	public int getSwitchToOrderMenu(){
		return switchToOrderMenu;
	}

	public R getR(){
		return R;
	}

	public int getIsTableReservationSupported(){
		return isTableReservationSupported;
	}

	public String getCurrency(){
		return currency;
	}

	public int getOpentableSupport(){
		return opentableSupport;
	}

	public String getId(){
		return id;
	}

	public UserRating getUserRating(){
		return userRating;
	}

	public List<Object> getOffers(){
		return offers;
	}

	public String getApikey(){
		return apikey;
	}

	public int getIsDeliveringNow(){
		return isDeliveringNow;
	}

	public String getDeeplink(){
		return deeplink;
	}

	public int getIsZomatoBookRes(){
		return isZomatoBookRes;
	}

	public String getFeaturedImage(){
		return featuredImage;
	}

	public String getUrl(){
		return url;
	}

	public String getCuisines(){
		return cuisines;
	}

	public String getEventsUrl(){
		return eventsUrl;
	}

	public String getName(){
		return name;
	}

	public Location getLocation(){
		return location;
	}

	public String getBookAgainUrl(){
		return bookAgainUrl;
	}

	public int getIsBookFormWebView(){
		return isBookFormWebView;
	}

	public String getBookFormWebViewUrl(){
		return bookFormWebViewUrl;
	}

	public String getMezzoProvider(){
		return mezzoProvider;
	}

	public String getPhotosUrl(){
		return photosUrl;
	}
}