package com.example.zomato.ui.restaurants.fragments.categoriesFragment.tabs.establishmentsFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.zomato.R;
import com.example.zomato.ui.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EstablishmentsFragment extends BaseFragment {

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.establishments_fragment,container,false);
         unbinder = ButterKnife.bind(this, view);
         return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
