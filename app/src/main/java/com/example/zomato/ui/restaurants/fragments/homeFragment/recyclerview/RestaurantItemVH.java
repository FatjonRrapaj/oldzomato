package com.example.zomato.ui.restaurants.fragments.homeFragment.recyclerview;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.R;
import com.example.zomato.client.responses.restaurants.RestaurantsItem;
import com.example.zomato.client.responses.restaurants.UserRating;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantItemVH extends RecyclerView.ViewHolder {

    @BindView(R.id.restaurant_imageview)
    ImageView restaurantImage;

    @BindView(R.id.restaurant_name_profile)
    TextView restaurantName;

    @BindView(R.id.restaurant_cuisines)
    TextView restaurantCuisines;

    @BindView(R.id.restaurant_custom_rating)
    TextView restaurantCustomRating;

    private String defaultRestaurantImage = "http://www.realdetroitweekly.com/wp-content/uploads/2017/06/Restaurants.jpg";
    private RestaurantsItem restaurantsItem;



    public RestaurantItemVH(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(restaurantsItem);
            }
        });
    }

    public void setData(RestaurantsItem restaurantsItem) {

        this.restaurantsItem = restaurantsItem;

        if (restaurantsItem.getRestaurant().getThumb().isEmpty()) {
            restaurantImage.setImageResource(R.drawable.ic_landscape);
        } else {
            Picasso.get().load(restaurantsItem.getRestaurant().getThumb()).into(restaurantImage);
        }

        restaurantName.setText(restaurantsItem.getRestaurant().getName());

        String[] cuisines = restaurantsItem.getRestaurant().getCuisines().split(",");
        String subCuisines = cuisines.length > 3 ? TextUtils.join(",",Arrays.copyOfRange(cuisines,0,3))
                : TextUtils.join(",",cuisines);
        restaurantCuisines.setText(subCuisines);

        UserRating userRating = restaurantsItem.getRestaurant().getUserRating();
        restaurantCustomRating.setText(userRating.getRatingText());
        String ratingColor = userRating.getRatingColor().isEmpty() ? "000" : userRating.getRatingColor();
        restaurantCustomRating.setTextColor(Color.parseColor("#" + ratingColor));
    }
}
