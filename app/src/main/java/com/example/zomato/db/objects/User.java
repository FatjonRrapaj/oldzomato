package com.example.zomato.db.objects;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private Long selectedCity;
    private String favouriteRestaurants;


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

    public Long getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(Long selectedCity) {
        this.selectedCity = selectedCity;
    }

    public String getFavouriteRestaurants() {
        return favouriteRestaurants;
    }

    public void setFavouriteRestaurants(String favouriteRestaurants) {
        this.favouriteRestaurants = favouriteRestaurants;
    }
}

