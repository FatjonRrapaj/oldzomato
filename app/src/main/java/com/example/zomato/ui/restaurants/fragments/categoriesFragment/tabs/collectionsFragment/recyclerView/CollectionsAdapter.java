package com.example.zomato.ui.restaurants.fragments.categoriesFragment.tabs.collectionsFragment.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.R;
import com.example.zomato.client.responses.collections.CollectionsItem;

import java.util.ArrayList;
import java.util.List;

public class CollectionsAdapter extends RecyclerView.Adapter<CollectionsItemVH> {

    private ArrayList<CollectionsItem> myList = new ArrayList<>();

    @NonNull
    @Override
    public CollectionsItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection,parent,false);
        return new CollectionsItemVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionsItemVH holder, int position) {
        holder.setData(myList.get(position));
    }

    @Override
    public int getItemCount() {
       return myList.size();
    }

    public void setData(List<CollectionsItem> myList){
        if (myList == null || myList.isEmpty()) {
            return;
        }
        this.myList.clear();
        this.myList.addAll(myList);
        notifyDataSetChanged();
    }
}
