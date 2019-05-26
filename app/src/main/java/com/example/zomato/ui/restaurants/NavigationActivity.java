package com.example.zomato.ui.restaurants;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.example.zomato.R;
import com.example.zomato.ui.base.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationActivity extends BaseActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_restaurants);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    toolbar.setTitle("Shop");
                    return true;
                case R.id.navigation_gifts:
                    toolbar.setTitle("My Gifts");
                    return true;
                case R.id.navigation_cart:
                    toolbar.setTitle("Cart");
                    return true;
            }
            return false;
        }
    };
}
