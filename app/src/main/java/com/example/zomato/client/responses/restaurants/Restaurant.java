package com.example.zomato.client.responses.restaurants;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Restaurant{

	@SerializedName("include_bogo_offers")
	private boolean includeBogoOffers;

	@SerializedName("has_online_delivery")
	private int hasOnlineDelivery;

	@SerializedName("establishment_types")
	private List<Object> establishmentTypes;

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

	public void setIncludeBogoOffers(boolean includeBogoOffers){
		this.includeBogoOffers = includeBogoOffers;
	}

	public boolean isIncludeBogoOffers(){
		return includeBogoOffers;
	}

	public void setHasOnlineDelivery(int hasOnlineDelivery){
		this.hasOnlineDelivery = hasOnlineDelivery;
	}

	public int getHasOnlineDelivery(){
		return hasOnlineDelivery;
	}

	public void setEstablishmentTypes(List<Object> establishmentTypes){
		this.establishmentTypes = establishmentTypes;
	}

	public List<Object> getEstablishmentTypes(){
		return establishmentTypes;
	}

	public void setHasTableBooking(int hasTableBooking){
		this.hasTableBooking = hasTableBooking;
	}

	public int getHasTableBooking(){
		return hasTableBooking;
	}

	public void setThumb(String thumb){
		this.thumb = thumb;
	}

	public String getThumb(){
		return thumb;
	}

	public void setAverageCostForTwo(int averageCostForTwo){
		this.averageCostForTwo = averageCostForTwo;
	}

	public int getAverageCostForTwo(){
		return averageCostForTwo;
	}

	public void setMenuUrl(String menuUrl){
		this.menuUrl = menuUrl;
	}

	public String getMenuUrl(){
		return menuUrl;
	}

	public void setPriceRange(int priceRange){
		this.priceRange = priceRange;
	}

	public int getPriceRange(){
		return priceRange;
	}

	public void setSwitchToOrderMenu(int switchToOrderMenu){
		this.switchToOrderMenu = switchToOrderMenu;
	}

	public int getSwitchToOrderMenu(){
		return switchToOrderMenu;
	}

	public void setR(R R){
		this.R = R;
	}

	public R getR(){
		return R;
	}

	public void setIsTableReservationSupported(int isTableReservationSupported){
		this.isTableReservationSupported = isTableReservationSupported;
	}

	public int getIsTableReservationSupported(){
		return isTableReservationSupported;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getCurrency(){
		return currency;
	}

	public void setOpentableSupport(int opentableSupport){
		this.opentableSupport = opentableSupport;
	}

	public int getOpentableSupport(){
		return opentableSupport;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUserRating(UserRating userRating){
		this.userRating = userRating;
	}

	public UserRating getUserRating(){
		return userRating;
	}

	public void setOffers(List<Object> offers){
		this.offers = offers;
	}

	public List<Object> getOffers(){
		return offers;
	}

	public void setApikey(String apikey){
		this.apikey = apikey;
	}

	public String getApikey(){
		return apikey;
	}

	public void setIsDeliveringNow(int isDeliveringNow){
		this.isDeliveringNow = isDeliveringNow;
	}

	public int getIsDeliveringNow(){
		return isDeliveringNow;
	}

	public void setDeeplink(String deeplink){
		this.deeplink = deeplink;
	}

	public String getDeeplink(){
		return deeplink;
	}

	public void setIsZomatoBookRes(int isZomatoBookRes){
		this.isZomatoBookRes = isZomatoBookRes;
	}

	public int getIsZomatoBookRes(){
		return isZomatoBookRes;
	}

	public void setFeaturedImage(String featuredImage){
		this.featuredImage = featuredImage;
	}

	public String getFeaturedImage(){
		return featuredImage;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setCuisines(String cuisines){
		this.cuisines = cuisines;
	}

	public String getCuisines(){
		return cuisines;
	}

	public void setEventsUrl(String eventsUrl){
		this.eventsUrl = eventsUrl;
	}

	public String getEventsUrl(){
		return eventsUrl;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	public void setBookAgainUrl(String bookAgainUrl){
		this.bookAgainUrl = bookAgainUrl;
	}

	public String getBookAgainUrl(){
		return bookAgainUrl;
	}

	public void setIsBookFormWebView(int isBookFormWebView){
		this.isBookFormWebView = isBookFormWebView;
	}

	public int getIsBookFormWebView(){
		return isBookFormWebView;
	}

	public void setBookFormWebViewUrl(String bookFormWebViewUrl){
		this.bookFormWebViewUrl = bookFormWebViewUrl;
	}

	public String getBookFormWebViewUrl(){
		return bookFormWebViewUrl;
	}

	public void setMezzoProvider(String mezzoProvider){
		this.mezzoProvider = mezzoProvider;
	}

	public String getMezzoProvider(){
		return mezzoProvider;
	}

	public void setPhotosUrl(String photosUrl){
		this.photosUrl = photosUrl;
	}

	public String getPhotosUrl(){
		return photosUrl;
	}
}