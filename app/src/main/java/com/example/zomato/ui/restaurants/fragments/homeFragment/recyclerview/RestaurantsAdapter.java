package com.example.zomato.ui.restaurants.fragments.homeFragment.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.R;
import com.example.zomato.client.responses.restaurants.RestaurantsItem;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantItemVH> {

    private ArrayList<RestaurantsItem> myList = new ArrayList<>();
    
    @NonNull
    @Override
    public RestaurantItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant,parent,false);
        return new RestaurantItemVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantItemVH holder, int position) {
        holder.setData(myList.get(position));
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public void setData(List<RestaurantsItem> myList) {
        if (myList == null || myList.isEmpty()) {
            return;
        }
        this.myList.clear();
        this.myList.addAll(myList);
        notifyDataSetChanged();
    }
}
