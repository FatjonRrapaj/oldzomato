package com.example.zomato.ui.restaurants.fragments.homeFragment.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantItem extends RecyclerView.ViewHolder {

    @BindView(R.id.restaurant_imageview)
    ImageView imageView;

    @BindView(R.id.restaurant_name)
    TextView restaurantName;

    @BindView(R.id.restaurant_cuisines)
    TextView restaurantCuisines;

    @BindView(R.id.restaurant_custom_rating)
    TextView restaurantCustomRating;

    public RestaurantItem(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: pass restaurant clicked
            }
        });
    }

    public void setData(){

    }
}
