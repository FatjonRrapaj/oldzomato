package com.example.zomato.db.objects;

public class Preferences {

    public String countryId;
    public String cities;
    public String cuisines;
    public String establishments;
    public String collections;
    public String favRestaurants;

    public Preferences() {
    }

    public String getCountryId() {
        return countryId;
    }

    public String getCities() {
        return cities;
    }

    public String getCuisines() {
        return cuisines;
    }

    public String getEstablishments() {
        return establishments;
    }

    public String getCollections() {
        return collections;
    }

    public String getFavRestaurants() {
        return favRestaurants;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public void setEstablishments(String establishments) {
        this.establishments = establishments;
    }

    public void setCollections(String collections) {
        this.collections = collections;
    }

    public void setFavRestaurants(String favRestaurants) {
        this.favRestaurants = favRestaurants;
    }
}
