package com.example.zomato.ui.restaurants;

import android.os.Bundle;
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
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

public class NavigationActivity extends BaseActivity implements FirstTimeLandingFragment.OnCitySelectedListener {

    private ActionBar toolbar;
    private User user;
    private User newUser;

    private FirebaseUser firebaseUser;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

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
        getCurrentUser();
    }

    private void getCurrentUser() {
        databaseReference = database.getReference("users").child(firebaseUser.getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    showHomeFragment();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getMessage());
            }
        });
    }

    private void showWelcomeFragment(){
        Bundle bundle = new Bundle();
        toolbar.setTitle("Welcome");
        bundle.putString("userName", user.getFirstName());
        FirstTimeLandingFragment firstTimeLandingFragment = new FirstTimeLandingFragment();
        firstTimeLandingFragment.setArguments(bundle);
        presentFragment(R.id.fragment_holder_nav, firstTimeLandingFragment);
    }

    private void showHomeFragment() {
        if (user==null){
            System.out.println("USER IS NULL");
            return;
        }
        if (user.getSelectedCityId() == null || user.getSelectedCityId() == 0) {
            showWelcomeFragment();
        } else {
            Bundle bundle = new Bundle();
            toolbar.setTitle("Home");
            bundle.putString("cityId", String.valueOf(user.getSelectedCityId()));
            HomeFragment homeFragment = new HomeFragment();
            homeFragment.setArguments(bundle);
            presentFragment(R.id.fragment_holder_nav, homeFragment);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Bundle bundle = new Bundle();
            switch (item.getItemId()) {
                case R.id.home:
                    showHomeFragment();
                    return true;
                case R.id.categories:
                    toolbar.setTitle("Categories");
//                    String[] favRestaurants = new String[user.getFavouriteRestaurants().size()];
//                    int i = 0;
//                    for (Map.Entry k: user.getFavouriteRestaurants().entrySet()) {
//                        favRestaurants[i] = (String) k.getValue();
//                        i++;
//                    }
//                    bundle.putStringArray("favRestaurants",favRestaurants);
                    CategoriesFragment categoriesFragment = new CategoriesFragment();
//                    categoriesFragment.setArguments(bundle);
                    presentFragment(R.id.fragment_holder_nav, categoriesFragment);
                    return true;
                case R.id.profile:
                    toolbar.setTitle("Profile");
                    Gson gson = new Gson();
                    String serializedUser = gson.toJson(user);
                    bundle.putString("user",serializedUser);
                    ProfileFragment profileFragment = new ProfileFragment();
                    profileFragment.setArguments(bundle);
                    presentFragment(R.id.fragment_holder_nav, profileFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void selectedCity(int cityId, String cityName) {
        databaseReference = database.getReference("users").child(firebaseUser.getUid());
        databaseReference.child("selectedCityId").setValue(cityId);
        databaseReference.child("selectedCityName").setValue(cityName);
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
            if (favouriteRestaurant.isChecked()) {
                databaseReference = database.getReference("users").child(firebaseUser.getUid());
                databaseReference.child("favouriteRestaurants")
                        .child(favouriteRestaurant.getId())
                        .setValue(favouriteRestaurant.getId());

            } else {
                databaseReference.child("favouriteRestaurants")
                        .child(favouriteRestaurant.getId())
                        .removeValue();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void signedOut(String signoutMessage) {
        if (signoutMessage != null && signoutMessage.equals( "Signed Out")) {
            finish();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void deletedCity(String deletedCity) {
        if(deletedCity != null && deletedCity.equals("Deleted city")){
            databaseReference = database.getReference("users").child(firebaseUser.getUid());
            databaseReference.child("selectedCityId").removeValue();
            databaseReference.child("selectedCityName").removeValue();
            showWelcomeFragment();
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
