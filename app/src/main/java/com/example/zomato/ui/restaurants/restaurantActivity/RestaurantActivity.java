package com.example.zomato.ui.restaurants.restaurantActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.zomato.R;
import com.example.zomato.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RestaurantActivity extends BaseActivity {

    @BindView(R.id.restairant_activity_toolbar)
    Toolbar toolbar;

    private Unbinder unbinder;
    private String restaurantId = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_activity);
        unbinder = ButterKnife.bind(this);
        restaurantId = getIntent().getStringExtra("restaurantId");
        toolbar.setTitle("Restaurant Name");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("restaurantId",restaurantId);
        RestaurantFragment restaurantFragment = new RestaurantFragment();
        restaurantFragment.setArguments(bundle);
        presentFragment(R.id.restaurant_activity_frame_layout,restaurantFragment);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
