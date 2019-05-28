package com.example.zomato.ui.restaurants.fragments.firstTimeLanding;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.R;
import com.example.zomato.R2;
import com.example.zomato.client.api.Api;
import com.example.zomato.client.api.ApiUtils;
import com.example.zomato.client.responses.cities.CitiesResponse;
import com.example.zomato.client.responses.cities.LocationSuggestionsItem;
import com.example.zomato.staticData.states.State;
import com.example.zomato.staticData.states.States;
import com.example.zomato.ui.base.BaseFragment;
import com.example.zomato.ui.restaurants.fragments.firstTimeLanding.recyclerViews.cities.CitiesAdapter;
import com.example.zomato.ui.restaurants.fragments.firstTimeLanding.recyclerViews.states.StatesAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstTimeLandingFragment extends BaseFragment {

    @BindView(R.id.welcome_first_landing)
    TextView welcomeText;

    @BindView(R2.id.states_recylerView)
    RecyclerView statesRecyclerView;

    @BindView(R.id.cities_recyclerview)
    RecyclerView citiesRecyclerView;

    @BindView(R.id.show_restaurants_first_landing)
    Button showCitiesButton;

    private Unbinder unbinder;
    private Api api;
    private RecyclerView.Adapter citiesAdapter;


    private OnCitySelectedListener listener;
    private String userName;
    private LocationSuggestionsItem city;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCitySelectedListener) {
            listener = (OnCitySelectedListener) context;
        } else {
            throw new ClassCastException(context.toString() + "must implement FirstTimeLandingFragment.OnCitySelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        api = ApiUtils.getApiService();
        if (getArguments() != null) {
            userName = getArguments().getString("userName");
        } else {
            userName = "test";
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_time_landing_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        welcomeText.setText("Welcome" + userName + ", in which city are you staying?");

        statesRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager statesLayoutManager = new LinearLayoutManager(FirstTimeLandingFragment.this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        statesRecyclerView.setLayoutManager(statesLayoutManager);
        RecyclerView.Adapter statesAdapter = new StatesAdapter();
        ((StatesAdapter) statesAdapter).setList(States.returnStates());
        statesRecyclerView.setAdapter(statesAdapter);

        citiesRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager citiesLayoutManager = new LinearLayoutManager(FirstTimeLandingFragment.this.getContext());
        citiesRecyclerView.setLayoutManager(citiesLayoutManager);
        citiesAdapter = new CitiesAdapter();
        citiesRecyclerView.setAdapter(citiesAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void userSelectedState(State state) {
        if (state != null) {
            city = null;
            showCitiesButton.setBackgroundColor(getResources().getColor(R.color.gray));
            getCities(state.getCities());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    void userSelectedCity(LocationSuggestionsItem city) {
        if (city != null) {
            this.city = city;
            showCitiesButton.setBackgroundColor(getResources().getColor(R.color.red));
        }
    }

    private void getCities(String cities) {
        showProgressBar("Fetching cities");
        api.getCitiesPerState(cities).enqueue(new Callback<CitiesResponse>() {
            @Override
            public void onResponse(Call<CitiesResponse> call, Response<CitiesResponse> response) {
                if (isSafe()) {
                    dismissProgressDialog();
                    ((CitiesAdapter) citiesAdapter).setList(response.body().getLocationSuggestions());
                }
            }

            @Override
            public void onFailure(Call<CitiesResponse> call, Throwable t) {
                if (isSafe()) {
                    dismissProgressDialog();
                    Toast.makeText(FirstTimeLandingFragment.this.getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick(R.id.show_restaurants_first_landing)
    void showRestaurantsClicked() {
        if (city == null) {
            Toast.makeText(FirstTimeLandingFragment.this.getContext(), "Please select a country and a city", Toast.LENGTH_SHORT).show();
            return;
        }
        listener.selectedCity(city.getId());
    }

    public interface OnCitySelectedListener {
        public void selectedCity(int cityId);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
