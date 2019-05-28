package com.example.zomato.ui.restaurants.fragments.categoriesFragment.tabs.favouritesFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.R;
import com.example.zomato.client.api.Api;
import com.example.zomato.client.api.ApiUtils;
import com.example.zomato.client.responses.singleRestaurant.SingleRestaurantResponse;
import com.example.zomato.db.objects.User;
import com.example.zomato.ui.base.BaseFragment;
import com.example.zomato.ui.restaurants.fragments.homeFragment.HomeFragment;
import com.example.zomato.ui.restaurants.fragments.homeFragment.recyclerview.RestaurantsAdapter;
import com.example.zomato.utils.RecyclerViewMargin;
import com.example.zomato.utils.SizeCalculator;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouritesFragment extends BaseFragment {

    @BindView(R.id.fav_restaurants_recyclerview)
    RecyclerView recyclerView;

    private Unbinder unbinder;

    private RecyclerView.Adapter adapter;

    private Api api;

    private FirebaseUser firebaseUser;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    private User user;
    private List<String> restaurantsArray = new ArrayList<>();
    private List<SingleRestaurantResponse> restaurantResponseList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = ApiUtils.getApiService();
        restaurantResponseList = new ArrayList<>();
    }

    private void getRestaurant(int restId){
        api.getRestaurant(String.valueOf(restId)).enqueue(new Callback<SingleRestaurantResponse>() {
            @Override
            public void onResponse(Call<SingleRestaurantResponse> call, Response<SingleRestaurantResponse> response) {
                if(response.body() != null){
                    restaurantResponseList.add(response.body());
                }
            }

            @Override
            public void onFailure(Call<SingleRestaurantResponse> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favourites_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new RestaurantsAdapter();
        int numberOfColumns = SizeCalculator.calculateNoOfColumns(FavouritesFragment.this.getContext(), 200);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(FavouritesFragment.this.getContext(), numberOfColumns);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        RecyclerViewMargin decoration = new RecyclerViewMargin(4, numberOfColumns);
        recyclerView.addItemDecoration(decoration);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
