package com.example.zomato.ui.restaurants.fragments.categoriesFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.zomato.R;
import com.example.zomato.ui.restaurants.fragments.categoriesFragment.tabs.establishmentsFragment.EstablishmentsFragment;
import com.example.zomato.ui.restaurants.fragments.categoriesFragment.tabs.cuisinesFragment.CuisinesFragment;
import com.example.zomato.utils.TabAdapter;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CategoriesFragment extends Fragment {

    @BindView(R.id.collections_tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.collections_view_pager)
    ViewPager viewPager;

    private Unbinder unbinder;

    private TabAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.categories_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int cityId = 61;
        if (getArguments() != null) {
            cityId = getArguments().getInt("cityId");
        }
        Bundle bundle = new Bundle();
        bundle.putInt("cityId", cityId);

        CuisinesFragment cuisinesFragment = new CuisinesFragment();
        cuisinesFragment.setArguments(bundle);
        EstablishmentsFragment establishmentsFragment = new EstablishmentsFragment();
        establishmentsFragment.setArguments(bundle);

        adapter = new TabAdapter(getFragmentManager());
        adapter.addFragment(cuisinesFragment,"Cuisines");
        adapter.addFragment(establishmentsFragment,"Categories");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
