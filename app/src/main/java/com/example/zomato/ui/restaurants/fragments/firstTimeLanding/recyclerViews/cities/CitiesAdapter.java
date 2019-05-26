package com.example.zomato.ui.restaurants.fragments.firstTimeLanding.recyclerViews.cities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.R;
import com.example.zomato.client.responses.cities.LocationSuggestionsItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CityItem> {

    private List<LocationSuggestionsItem> myList = new ArrayList<>();

    @NonNull
    @Override
    public CityItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city,parent,false);
        return new CityItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityItem holder, int position) {
        holder.setData(myList.get(position));
    }

    @Override
    public int getItemCount() {
        return filterEmptyNamesOut(myList).size();
    }

    public void setList(List<LocationSuggestionsItem> myList) {
        if (myList == null || myList.isEmpty()) {
            return;
        }
        this.myList.clear();
        this.myList.addAll(filterEmptyNamesOut(myList));
        notifyDataSetChanged();
    }

    private ArrayList<LocationSuggestionsItem> filterEmptyNamesOut (List<LocationSuggestionsItem> unFilteredList) {
        ArrayList<LocationSuggestionsItem> filteredList = new ArrayList<>();
        filteredList.addAll(unFilteredList);
        for (Iterator<LocationSuggestionsItem> it = myList.iterator(); it.hasNext();) {
            if (it.next().getName().isEmpty())
                it.remove();
        }
        return filteredList;
    }
}
