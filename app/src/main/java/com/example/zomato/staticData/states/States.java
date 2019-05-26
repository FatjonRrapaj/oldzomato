package com.example.zomato.staticData.states;

import com.example.zomato.R;

import java.util.ArrayList;

public class States {
    public static ArrayList<State> returnStates() {
        //TODO: build sharedpreferences to save the selected state and city,so user can see it directly
        //TODO: maybe add to shared preferences even the viewed restaurants,so he can view them again at favourites...
        ArrayList<State> states = new ArrayList<>();
        //todo: remove cities from this array and make them at the call
        //todo: if(id==x) make this call,else this call else this call else this call
        State portugal = new State();
        portugal.setName("Portugal");
        portugal.setId(Constants.PORTUGAL);
        portugal.setCities(Constants.PORTUGAL_CITIES);
        portugal.setCapitalCity(Constants.PORTUGAL_GREATER_LISBON);
        portugal.setResource(R.drawable.portugal);
        states.add(portugal);

        State turkey = new State();
        turkey.setName("Turkey");
        turkey.setId(Constants.TURKEY);
        turkey.setCities(Constants.TURKEY_CITIES);
        turkey.setCapitalCity(Constants.TURKEY_ANKARA);
        turkey.setResource(R.drawable.turkey);
        states.add(turkey);

        State uk = new State();
        uk.setName("UK");
        uk.setId(Constants.UNITED_KINGDOM);
        uk.setCities(Constants.UK_CITIES);
        uk.setCapitalCity(Constants.UK_LONDON);
        uk.setResource(R.drawable.united_kingdom);
        states.add(uk);

        State ireland = new State();
        ireland.setName("Ireland");
        ireland.setId(Constants.IRELAND);
        ireland.setCities(Constants.IRELAND_CITIES);
        ireland.setCapitalCity(Constants.IRELAND_DUBLIN);
        ireland.setResource(R.drawable.ireland);
        states.add(ireland);

        State poland = new State();
        poland.setName("Poland");
        poland.setCapitalCity(Constants.POLAND_WARSAW);
        poland.setId(Constants.POLAND);
        poland.setCities(Constants.POLAND_CITIES);
        poland.setResource(R.drawable.poland);
        states.add(poland);

        State italy = new State();
        italy.setName("Italy");
        italy.setId(Constants.ITALY);
        italy.setCities(Constants.ITALY_CITIES);
        italy.setCapitalCity(Constants.ITALY_ROME);
        italy.setResource(R.drawable.italy);
        states.add(italy);

        return states;
    }
}
