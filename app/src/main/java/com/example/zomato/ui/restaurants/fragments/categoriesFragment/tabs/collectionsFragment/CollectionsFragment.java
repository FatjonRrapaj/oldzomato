package com.example.zomato.ui.restaurants.fragments.categoriesFragment.tabs.collectionsFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.R;
import com.example.zomato.client.api.Api;
import com.example.zomato.client.api.ApiUtils;
import com.example.zomato.client.responses.collections.CollectionsItem;
import com.example.zomato.client.responses.collections.CollectionsResponse;
import com.example.zomato.client.responses.restaurants.RestaurantsResponse;
import com.example.zomato.ui.base.BaseFragment;
import com.example.zomato.ui.restaurants.fragments.categoriesFragment.tabs.collectionsFragment.recyclerView.CollectionsAdapter;
import com.example.zomato.ui.restaurants.fragments.homeFragment.recyclerview.RestaurantsAdapter;
import com.example.zomato.utils.RecyclerViewMargin;
import com.example.zomato.utils.SizeCalculator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionsFragment extends BaseFragment {

    @BindView(R.id.collections_recyclerview)
    RecyclerView collectionsRecyclerView;

    @BindView(R.id.collections_restaurants_recyclerview)
    RecyclerView restaurantsRecyclerView;

    private Unbinder unbinder;
    private Api api;

    private RecyclerView.Adapter collectionsAdapter;
    private RecyclerView.Adapter restaurantsAdapter;

    private String cityId = "61";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            if (getArguments().getString("cityId") != null) {
                cityId = getArguments().getString("cityId");
            } else {
                Log.e("city id is","NULL");
            }

        }

        api = ApiUtils.getApiService();
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.collections_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void setUpRestaurantsRecyclerView() {
        restaurantsAdapter = new RestaurantsAdapter();
        int numberOfColumns = SizeCalculator.calculateNoOfColumns(CollectionsFragment.this.getContext(), 200);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(CollectionsFragment.this.getContext(), numberOfColumns);
        restaurantsRecyclerView.setLayoutManager(gridLayoutManager);
        restaurantsRecyclerView.setAdapter(restaurantsAdapter);
        restaurantsRecyclerView.setHasFixedSize(true);
        RecyclerViewMargin decoration = new RecyclerViewMargin(4, numberOfColumns);
        restaurantsRecyclerView.addItemDecoration(decoration);
    }

    private void setUpCollectionsRecyclerView() {
        collectionsAdapter = new CollectionsAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CollectionsFragment.this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        collectionsRecyclerView.setHasFixedSize(true);
        collectionsRecyclerView.setLayoutManager(layoutManager);
        collectionsRecyclerView.setAdapter(collectionsAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpCollectionsRecyclerView();
        setUpRestaurantsRecyclerView();
        getCollectionsPerCity(cityId);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void collectionSelected(CollectionsItem collectionsItem) {
        if (collectionsItem != null) {
            getRestaurantPerCollection(String.valueOf(collectionsItem.getCollection().getCollectionId()));
        }
    }

    private void getCollectionsPerCity(String cityId) {
        showProgressBar("Fetching Collections...");
        api.getCollectionsPerCity(cityId).enqueue(new Callback<CollectionsResponse>() {
            @Override
            public void onResponse(Call<CollectionsResponse> call, Response<CollectionsResponse> response) {
                if (isSafe()) {
                    dismissProgressDialog();
                    if (response.body() != null || response.body().getCollections() != null && !response.body().getCollections().isEmpty()) {
                        ((CollectionsAdapter) collectionsAdapter).setData(response.body().getCollections());
                        getRestaurantPerCollection(String.valueOf(response.body().getCollections().get(0).getCollection().getCollectionId()));
                    } else {
                        Toast.makeText(CollectionsFragment.this.getContext(), "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CollectionsResponse> call, Throwable t) {
                if (isSafe()) {
                    Toast.makeText(CollectionsFragment.this.getContext(), "Something went wrong, please check your connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getRestaurantPerCollection(String collectionId) {
        showProgressBar("Fetching Restaurants...");
        api.getRestaurantsPerCollection(cityId, "city", collectionId).enqueue(new Callback<RestaurantsResponse>() {
            @Override
            public void onResponse(Call<RestaurantsResponse> call, Response<RestaurantsResponse> response) {
                if (isSafe()) {
                    dismissProgressDialog();
                    if (response.body().getRestaurants() != null && !response.body().getRestaurants().isEmpty()) {
                        ((RestaurantsAdapter) restaurantsAdapter).setData(response.body().getRestaurants());
                    } else {
                        if (isSafe()) {
                            Toast.makeText(CollectionsFragment.this.getContext(), "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<RestaurantsResponse> call, Throwable t) {
                if (isSafe()) {
                    dismissProgressDialog();
                    Toast.makeText(CollectionsFragment.this.getContext(), "Something went wrong, please check your connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
