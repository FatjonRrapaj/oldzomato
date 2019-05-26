package com.example.zomato.ui.restaurants.fragments.firstTimeLanding.recyclerViews.states;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.R;
import com.example.zomato.staticData.states.State;
import com.example.zomato.ui.restaurants.fragments.firstTimeLanding.recyclerViews.states.StateItem;

import java.util.ArrayList;
import java.util.List;

public class StatesAdapter extends RecyclerView.Adapter<StateItem> {

    private List<State> myList = new ArrayList<>();


    @NonNull
    @Override
    public StateItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_state,parent,false);
        return new StateItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateItem holder, int position) {
        holder.setData(myList.get(position));
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public void setList(List<State> myList) {
        if (myList == null || myList.isEmpty()) {
            return;
        }
        this.myList.clear();
        this.myList.addAll(myList);
        notifyDataSetChanged();
    }
}
