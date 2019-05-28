package com.example.zomato.ui.restaurants;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import com.example.zomato.R;
import com.example.zomato.client.responses.restaurants.RestaurantsItem;
import com.example.zomato.db.objects.User;
import com.example.zomato.eventBus.FavouriteRestaurant;
import com.example.zomato.ui.base.BaseActivity;
import com.example.zomato.ui.restaurants.fragments.categoriesFragment.CategoriesFragment;
import com.example.zomato.ui.restaurants.fragments.homeFragment.HomeFragment;
import com.example.zomato.ui.restaurants.fragments.firstTimeLanding.FirstTimeLandingFragment;
import com.example.zomato.ui.restaurants.fragments.profileFragment.ProfileFragment;
import com.example.zomato.ui.restaurants.restaurantActivity.RestaurantActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class NavigationActivity extends BaseActivity implements FirstTimeLandingFragment.OnCitySelectedListener {

    private ActionBar toolbar;
    private User user;

    private FirebaseUser firebaseUser;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    //todo: delete this variable and the logic after storing to the db
//    private int selectedCityId = 61;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        EventBus.getDefault().register(this);
        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        database = FirebaseDatabase.getInstance();
        Gson gson = new Gson();
        firebaseUser = getFirebaseUser();

        if (getIntent().getStringExtra("newUser") != null) {
            databaseReference = database.getReference();
            user = gson.fromJson(getIntent().getStringExtra("newUser"), User.class);
            databaseReference.child("users").child(firebaseUser.getUid()).setValue(user);
        } else {
            getCurrentUser();
        }
    }

    private void getCurrentUser() {
        databaseReference = database.getReference("users").child(firebaseUser.getUid());

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                System.out.println(user + "THIS IS THE USERRR");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void showHomeFragment() {
        Bundle bundle = new Bundle();
        //first check if there is a user in the db.
        //to reopen firsttimelanding go to profile and delete selected city.
        if (user.getSelectedCity() == 0) {
            toolbar.setTitle("Welcome");
            bundle.putString("userName", "");
            FirstTimeLandingFragment firstTimeLandingFragment = new FirstTimeLandingFragment();
            firstTimeLandingFragment.setArguments(bundle);
            presentFragment(R.id.fragment_holder_nav, firstTimeLandingFragment);
        } else {
            toolbar.setTitle("Home");
            bundle.putString("cityId", String.valueOf(user.getSelectedCity()));
            HomeFragment homeFragment = new HomeFragment();
            homeFragment.setArguments(bundle);
            presentFragment(R.id.fragment_holder_nav, homeFragment);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    showHomeFragment();
                    return true;
                case R.id.categories:
                    toolbar.setTitle("Categories");
                    presentFragment(R.id.fragment_holder_nav, new CategoriesFragment());
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
        databaseReference.child("users").child(firebaseUser.getUid()).child("selectedCity").setValue(cityId);
        Bundle bundle = new Bundle();
        toolbar.setTitle("Home");
        bundle.putInt("cityId", cityId);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        presentFragment(R.id.fragment_holder_nav, homeFragment);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void selectedRestaurant(RestaurantsItem restaurantsItem) {
        if (restaurantsItem != null) {
            presentActivity(new RestaurantActivity(), "restaurantId", restaurantsItem.getRestaurant().getId());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void selectedFavouriteRestaurant(FavouriteRestaurant favouriteRestaurant) {
        if (favouriteRestaurant != null) {
            getCurrentUser();
            if (user.getFavouriteRestaurants() == null || user.getFavouriteRestaurants().isEmpty()) {
                if (favouriteRestaurant.isChecked()) {
                    databaseReference.child("favouriteRestaurants")
                            .setValue(favouriteRestaurant.getId() + ",");
                }
            } else {
                String[] restaurantsArray = user.getFavouriteRestaurants().split(",");
                List<String> result = new ArrayList<>(Arrays.asList(restaurantsArray));
                if (favouriteRestaurant.isChecked()) {
                    if (!result.contains(favouriteRestaurant.getId())) {
                        result.add(favouriteRestaurant.getId() + ",");
                    }
                } else {
                    if (result.contains(favouriteRestaurant.getId())) {
                        for (Iterator<String> it = result.iterator(); it.hasNext(); ) {
                            if (it.next().equals(favouriteRestaurant.getId()))
                                it.remove();
                        }
                    }
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (String restId : result) {
                    stringBuilder.append(restId);
                    stringBuilder.append(",");
                }
                String newFavRestaurants = stringBuilder.toString();
                Log.e("new restaurants array", newFavRestaurants);
                databaseReference.child("users")
                        .child(firebaseUser.getUid())
                        .child("favouriteRestaurants")
                        .setValue(newFavRestaurants);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void signedOut(String signoutMessage) {
        if (signoutMessage != null && signoutMessage == "Signed Out") {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
