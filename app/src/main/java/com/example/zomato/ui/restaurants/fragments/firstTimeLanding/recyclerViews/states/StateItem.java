package com.example.zomato.ui.restaurants.fragments.firstTimeLanding.recyclerViews.states;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.R;
import com.example.zomato.staticData.states.State;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class StateItem extends RecyclerView.ViewHolder {

    private State state;

    @BindView(R.id.state_image)
    ImageButton stateImage;

    @BindView(R.id.state_name)
    TextView stateName;

    @OnClick(R.id.state_image)
    void stateClicked(){
        EventBus.getDefault().post(state);
    }

    public StateItem(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(State state){
        this.state = state;
        stateImage.setImageResource(state.getResource());
        stateName.setText(state.getName());
    }
}
