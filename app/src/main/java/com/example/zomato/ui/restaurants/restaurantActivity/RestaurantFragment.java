package com.example.zomato.ui.restaurants.restaurantActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.zomato.R;
import com.example.zomato.client.api.Api;
import com.example.zomato.client.api.ApiUtils;
import com.example.zomato.client.responses.singleRestaurant.Location;
import com.example.zomato.client.responses.singleRestaurant.SingleRestaurantResponse;
import com.example.zomato.eventBus.FavouriteRestaurant;
import com.example.zomato.ui.base.BaseFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantFragment extends BaseFragment implements OnMapReadyCallback {

    @BindView(R.id.restaurant_image_profile)
    ImageView restaurantImage;

    @BindView(R.id.restaurant_name_profile)
    TextView restaurantName;

    @BindView(R.id.restaurant_address)
    TextView restaurantAddress;

    @BindView(R.id.currency)
    TextView currency;

    @BindView(R.id.cost_for_two)
    TextView costForTwo;

    @BindView(R.id.rating_card_view)
    CardView ratingCardView;

    @BindView(R.id.rating_textview)
    TextView ratingTextView;

    @BindView(R.id.favourite_checkbox)
    CheckBox favouriteCheckbox;

    private Unbinder unbinder;
    private Api api;

    private GoogleMap googleMap;
    private Marker marker;

    private SingleRestaurantResponse restaurantObj;
    private String restaurantId = "";



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restaurantId = getArguments().getString("restaurantId");
        }
        api = ApiUtils.getApiService();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.restaurant_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        handleCheckboxClick();
        getRestaurant(restaurantId);
    }

    private void updateMap(Location location) {
        Double latidute = Double.parseDouble(location.getLatitude());
        Double longitude = Double.parseDouble(location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(latidute, longitude));
        marker = googleMap.addMarker(markerOptions);
        marker.setPosition(new LatLng(latidute, longitude));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 14));
    }

    private void updateRestaurantProfile(SingleRestaurantResponse restaurant) {
        updateMap(restaurant.getLocation());
        if (restaurant.getThumb().isEmpty()) {
            restaurantImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_landscape));
        } else {
            Picasso.get().load(restaurant.getThumb()).into(restaurantImage);

        }
        restaurantName.setText(restaurant.getName());
        restaurantAddress.setText(restaurant.getLocation().getAddress());
        currency.setText(restaurant.getCurrency());
        costForTwo.setText(restaurant.getAverageCostForTwo() + restaurant.getCurrency());
        ratingCardView.setCardBackgroundColor(Color.parseColor("#" + restaurant.getUserRating().getRatingColor()));
        ratingTextView.setText(restaurant.getUserRating().getRatingText());
    }

    private void handleCheckboxClick() {
        favouriteCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EventBus.getDefault().post(new FavouriteRestaurant(isChecked, restaurantObj.getId()));
            }
        });
    }

    private void getRestaurant(String restaurantId) {
        showProgressBar("Preparing restaurant data...");
        api.getRestaurant(restaurantId).enqueue(new Callback<SingleRestaurantResponse>() {
            @Override
            public void onResponse(Call<SingleRestaurantResponse> call, Response<SingleRestaurantResponse> response) {
                if (isSafe()) {
                    dismissProgressDialog();
                    if (response.body() != null) {
                        restaurantObj = response.body();
                        updateRestaurantProfile(response.body());
                    } else {
                        Toast.makeText(RestaurantFragment.this.getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SingleRestaurantResponse> call, Throwable t) {
                if (isSafe()) {
                    dismissProgressDialog();
                    Toast.makeText(RestaurantFragment.this.getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
