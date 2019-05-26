package com.example.zomato.ui.restaurants.fragments.firstTimeLanding.recyclerViews.cities;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.R;
import com.example.zomato.client.responses.cities.LocationSuggestionsItem;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CityItem extends RecyclerView.ViewHolder {

    private LocationSuggestionsItem locationSuggestionsItem;

    @BindView(R.id.city_item_name)
    Button button;

    @OnClick(R.id.city_item_name)
    void cityClicked() {
        EventBus.getDefault().post(locationSuggestionsItem);
    }

    public CityItem(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(LocationSuggestionsItem locationSuggestionsItem) {
        this.locationSuggestionsItem = locationSuggestionsItem;
        button.setText(locationSuggestionsItem.getName());
    }

}
