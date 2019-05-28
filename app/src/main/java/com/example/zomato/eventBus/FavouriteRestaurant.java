package com.example.zomato.eventBus;

public class FavouriteRestaurant {

    private boolean checked;
    private String id;

    public FavouriteRestaurant(boolean checked, String id) {
        this.checked = checked;
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public String getId() {
        return id;
    }
}
