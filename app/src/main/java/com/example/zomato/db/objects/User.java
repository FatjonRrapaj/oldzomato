package com.example.zomato.db.objects;

import java.util.HashMap;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private Long selectedCityId;
    private String selectedCityName;
    private HashMap<String ,String> favouriteRestaurants;


    public User() {
    }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getSelectedCityId() {
        return selectedCityId;
    }

    public void setSelectedCityId(Long selectedCityId) {
        this.selectedCityId = selectedCityId;
    }

    public HashMap<String ,String> getFavouriteRestaurants() {
        return favouriteRestaurants;
    }

    public void setFavouriteRestaurants(HashMap<String ,String> favouriteRestaurants) {
        this.favouriteRestaurants = favouriteRestaurants;
    }

    public String getSelectedCityName() {
        return selectedCityName;
    }

    public void setSelectedCityName(String selectedCityName) {
        this.selectedCityName = selectedCityName;
    }
}

