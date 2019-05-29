package com.example.zomato;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class ZomatoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
