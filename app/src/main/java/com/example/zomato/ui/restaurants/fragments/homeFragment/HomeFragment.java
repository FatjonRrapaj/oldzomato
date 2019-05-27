package com.example.zomato.ui.restaurants.fragments.homeFragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.R;
import com.example.zomato.client.api.Api;
import com.example.zomato.client.api.ApiUtils;
import com.example.zomato.client.responses.restaurants.RestaurantsResponse;
import com.example.zomato.ui.base.BaseFragment;
import com.example.zomato.ui.restaurants.fragments.homeFragment.recyclerview.RestaurantsAdapter;
import com.example.zomato.utils.CustomScrollListener;
import com.example.zomato.utils.RecyclerViewMargin;
import com.example.zomato.utils.SizeCalculator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends BaseFragment implements CustomScrollListener.OnScrollChangedListener {

    @BindView(R.id.home_seek_bar_container)
    ConstraintLayout seekBarContainer;

    @BindView(R.id.home_seekbar_progress)
    TextView seekBarProgress;

    @BindView(R.id.home_seek_bar)
    SeekBar seekBar;

    @BindView(R.id.home_restaurants_recylcerview)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private int cityId = 61; //London default.
    private Api api;

    private int seekBarContainerHeight;
    private RecyclerView.Adapter adapter;

    float scale;
    int seekBarContainerFolded;
    int seekBarContainerExpanded;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (getArguments() != null) {
            cityId = getArguments().getInt("cityId");
        }
        scale = HomeFragment.this.getContext().getResources().getDisplayMetrics().density;
        seekBarContainerExpanded = (int) (70 * scale + 0.5f);
        seekBarContainerFolded = (int) (0 * scale + 0.5f);

        api = ApiUtils.getApiService();
        return view;
    }

    private void handleSeekbarChange() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    seekBarProgress.setText(String.valueOf(seekBar.getProgress()));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void hanldeRecyclerViewScrollListener() {
        recyclerView.addOnScrollListener(new CustomScrollListener(this));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new RestaurantsAdapter();
        int numberOfColumns = SizeCalculator.calculateNoOfColumns(HomeFragment.this.getContext(), 200);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(HomeFragment.this.getContext(), numberOfColumns);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        RecyclerViewMargin decoration = new RecyclerViewMargin(4, numberOfColumns);
        recyclerView.addItemDecoration(decoration);
        hanldeRecyclerViewScrollListener();

        seekBarProgress.setText(String.valueOf(seekBar.getProgress()));
        handleSeekbarChange();

        getSeekbarContainerHeight();

        fetchRestaurants(seekBar.getProgress());
    }

    private void getSeekbarContainerHeight() {
        ViewTreeObserver vto = seekBarContainer.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    seekBarContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    seekBarContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                seekBarContainerHeight = seekBarContainer.getMeasuredHeight();
            }
        });
    }

    private void fetchRestaurants(int count) {
        showProgressBar("Fetching Restaurants...");
        api.getRestaurantsPerEntity(cityId, "city", count).enqueue(new Callback<RestaurantsResponse>() {
            @Override
            public void onResponse(Call<RestaurantsResponse> call, Response<RestaurantsResponse> response) {
                dismissProgressDialog();
                if (response.body().getRestaurants() != null || !response.body().getRestaurants().isEmpty()) {
                    ((RestaurantsAdapter) adapter).setData(response.body().getRestaurants());
                } else {
                    Toast.makeText(HomeFragment.this.getContext(), "Something went wrong please try again later", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RestaurantsResponse> call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(HomeFragment.this.getContext(), "Something went wrong please check your connection", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @OnClick(R.id.home_go_seek_bar_button)
    void goClicked() {
        fetchRestaurants(seekBar.getProgress());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void scrolledDown() {
//        if (seekBarContainerHeight == seekBarContainerExpanded) {
//            seekBarContainer.setLayoutParams(
//                    new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, seekBarContainerFolded)
//            );
//        }
    }

    @Override
    public void scrolledUp() {
//        if (seekBarContainerHeight <= seekBarContainerFolded) {
//            seekBarContainer.setLayoutParams(
//                    new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, seekBarContainerExpanded)
//            );
//        }

    }

    @Override
    public void stoppedScrolling() {
    }
}
