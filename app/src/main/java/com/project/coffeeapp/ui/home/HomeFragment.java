package com.project.coffeeapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.project.coffeeapp.R;
import com.project.coffeeapp.adapters.MainItemHomeAdapter;
import com.project.coffeeapp.adapters.SliderAdapter;
import com.project.coffeeapp.databinding.FragmentHomeBinding;
import com.project.coffeeapp.interfaces.IOnClickViewItem;
import com.project.coffeeapp.models.Advertise;
import com.project.coffeeapp.models.Coffee;
import com.project.coffeeapp.models.ItemHome;
import com.project.coffeeapp.views.DetailActivity;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private MainItemHomeAdapter mainAdapter;
    private List<ItemHome> mListItemHome;
    private List<Coffee> mListCoffee;
    private List<Advertise> mListAdvertise;
    private SliderAdapter sliderAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Set slider
        sliderAdapter = new SliderAdapter(getContext(), mListAdvertise);
        binding.sliderHome.setSliderAdapter(sliderAdapter);
        binding.sliderHome.setIndicatorAnimation(IndicatorAnimationType.WORM);
        binding.sliderHome.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        binding.sliderHome.startAutoCycle();

        binding.rvHome.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        homeViewModel.getCoffee().observe(getViewLifecycleOwner(), new Observer<List<Coffee>>() {
            @Override
            public void onChanged(List<Coffee> coffees) {
                mListCoffee = coffees;
                mListItemHome = getListItemHome();
                mainAdapter = new MainItemHomeAdapter(getContext(), mListItemHome, new IOnClickViewItem() {
                    @Override
                    public void onClick(Coffee coffee) {
                        Intent intent = new Intent(getContext(), DetailActivity.class);
                        intent.putExtra(DetailActivity.EXTRA_COFFEE, coffee);
                        startActivity(intent);
                    }
                });
                binding.rvHome.setAdapter(mainAdapter);
            }
        });
        homeViewModel.getAdvertise().observe(getViewLifecycleOwner(), new Observer<List<Advertise>>() {
            @Override
            public void onChanged(List<Advertise> advertises) {
                mListAdvertise = advertises;
                sliderAdapter.setListAdvertise(advertises);
            }
        });

        homeViewModel.callAPIGetAdvertise();
        homeViewModel.callAPIGetCoffee();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public List<ItemHome> getListItemHome(){
        List<ItemHome> itemHomes = new ArrayList<>();
        itemHomes.add(new ItemHome(0, "SPECIAL TODAY", mListCoffee));
        itemHomes.add(new ItemHome(1, "POPULAR", mListCoffee));
        return itemHomes;
    }
}