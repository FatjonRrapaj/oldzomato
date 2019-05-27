package com.example.zomato.ui.restaurants;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import com.example.zomato.R;
import com.example.zomato.ui.base.BaseActivity;
import com.example.zomato.ui.restaurants.fragments.collectionsFragment.CollectionsFragment;
import com.example.zomato.ui.restaurants.fragments.homeFragment.HomeFragment;
import com.example.zomato.ui.restaurants.fragments.firstTimeLanding.FirstTimeLandingFragment;
import com.example.zomato.ui.restaurants.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationActivity extends BaseActivity implements FirstTimeLandingFragment.OnCitySelectedListener {

    private ActionBar toolbar;
    private String userName;

    //todo: delete this variable and the logic after storing to the db
    private int selectedCityId = 61;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (getIntent().getStringExtra("userName") != null) {
            userName = getIntent().getStringExtra("userName");
        } else {
            userName = "";
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    Bundle bundle = new Bundle();
                    //first check if there is a user in the db.
                    //to reopen firsttimelanding go to profile and delete selected city.
                    if (selectedCityId == 0) {
                        toolbar.setTitle("Welcome");
                        bundle.putString("userName",userName);
                        FirstTimeLandingFragment firstTimeLandingFragment = new FirstTimeLandingFragment();
                        firstTimeLandingFragment.setArguments(bundle);
                        presentFragment(R.id.fragment_holder_nav, firstTimeLandingFragment);
                    } else {
                        bundle.putInt("cityId",selectedCityId);
                        HomeFragment homeFragment = new HomeFragment();
                        homeFragment.setArguments(bundle);
                        presentFragment(R.id.fragment_holder_nav,homeFragment);
                    }
                    return true;
                case R.id.collections:
                    toolbar.setTitle("Collections");
                    presentFragment(R.id.fragment_holder_nav, new CollectionsFragment());
                    return true;
                case R.id.profile:
                    toolbar.setTitle("Profile");
                    presentFragment(R.id.fragment_holder_nav, new ProfileFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    public void selectedCity(int cityId) {
        //TODO: store the city id to the db.
    }
}
