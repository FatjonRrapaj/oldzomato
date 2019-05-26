package com.example.zomato.data.states;

public class State {

    private String name;
    private String id;
    private String cities;
    private int resource;
    private String capitalCity;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getResource() {
        return resource;
    }

    public String getCapitalCity() {
        return capitalCity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public void setCapitalCity(String capitalCity) {
        this.capitalCity = capitalCity;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public String getCities() {
        return cities;
    }

}
