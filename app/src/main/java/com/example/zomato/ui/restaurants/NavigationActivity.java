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
                showHomeFragment();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getMessage());
            }
        });
    }

    private void showHomeFragment() {
        Bundle bundle = new Bundle();
        if (user.getSelectedCityId() == 0) {
            toolbar.setTitle("Welcome");
            bundle.putString("userName", user.getFirstName());
            FirstTimeLandingFragment firstTimeLandingFragment = new FirstTimeLandingFragment();
            firstTimeLandingFragment.setArguments(bundle);
            presentFragment(R.id.fragment_holder_nav, firstTimeLandingFragment);
        } else {
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
                    Bundle bundle = new Bundle();
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
                databaseReference.child("users")
                        .child(firebaseUser.getUid())
                        .child("favouriteRestaurants")
                        .child(favouriteRestaurant.getId())
                        .setValue(favouriteRestaurant.getId());

            } else {
                databaseReference.child("users")
                        .child(firebaseUser.getUid())
                        .child("favouriteRestaurants")
                        .child(favouriteRestaurant.getId())
                        .removeValue();
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
