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
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.project.coffeeapp.R;
import com.project.coffeeapp.adapters.CategoryHomeAdapter;
import com.project.coffeeapp.adapters.MainItemHomeAdapter;
import com.project.coffeeapp.adapters.SliderAdapter;
import com.project.coffeeapp.databinding.FragmentHomeBinding;
import com.project.coffeeapp.interfaces.IOnClickViewCategory;
import com.project.coffeeapp.interfaces.IOnClickViewItem;
import com.project.coffeeapp.models.Advertise;
import com.project.coffeeapp.models.Category;
import com.project.coffeeapp.models.Coffee;
import com.project.coffeeapp.models.ItemHome;
import com.project.coffeeapp.views.DetailActivity;
import com.project.coffeeapp.views.SelectedCategoryActivity;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private MainItemHomeAdapter mainAdapter;
    private List<ItemHome> mListItemHome;
    private List<Coffee> mListCoffee;
    private List<Coffee> mListTopFavourite;
    private List<Advertise> mListAdvertise;
    private List<Category> mListCategory;
    private SliderAdapter sliderAdapter;
    private RecyclerView rvCategory;
    private RecyclerView rvHome;
    private SliderView sliderHome;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        rvCategory = binding.rvCategory;
        sliderHome = binding.sliderHome;
        rvHome = binding.rvHome;
        // Set slider
        sliderAdapter = new SliderAdapter(getContext(), mListAdvertise);
        sliderHome.setSliderAdapter(sliderAdapter);
        sliderHome.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderHome.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderHome.startAutoCycle();

        rvHome.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // For coffee product
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
                rvHome.setAdapter(mainAdapter);
            }
        });
        // For slide advertisement
        homeViewModel.getAdvertise().observe(getViewLifecycleOwner(), new Observer<List<Advertise>>() {
            @Override
            public void onChanged(List<Advertise> advertises) {
                mListAdvertise = advertises;
                sliderAdapter.setListAdvertise(advertises);
            }
        });

        // For rv category
        homeViewModel.getCategory().observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                mListCategory = categories;
                rvCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                rvCategory.setAdapter(new CategoryHomeAdapter(getContext(), mListCategory, new IOnClickViewCategory() {
                    @Override
                    public void OnClick(Category category) {
                        Intent intent = new Intent(getContext(), SelectedCategoryActivity.class);
                        intent.putExtra(SelectedCategoryActivity.EXTRA_CATEGORY, category);
                        startActivity(intent);
                    }
                }));
            }
        });

        // For top favourite
        homeViewModel.getTopFavourite().observe(getViewLifecycleOwner(), new Observer<List<Coffee>>() {
            @Override
            public void onChanged(List<Coffee> coffees) {
                mListTopFavourite = coffees;
                mListItemHome = getListItemHome();
                mainAdapter = new MainItemHomeAdapter(getContext(), mListItemHome, new IOnClickViewItem() {
                    @Override
                    public void onClick(Coffee coffee) {
                        Intent intent = new Intent(getContext(), DetailActivity.class);
                        intent.putExtra(DetailActivity.EXTRA_COFFEE, coffee);
                        startActivity(intent);
                    }
                });
                rvHome.setAdapter(mainAdapter);
            }
        });

        homeViewModel.callAPIGetAdvertise();
        homeViewModel.callAPIGetCoffee();
        homeViewModel.callAPIGetCategory();
        homeViewModel.callAPITopFavourite();

        return root;
    }

    @Override
    public void onResume() {
        homeViewModel.callAPITopFavourite();
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public List<ItemHome> getListItemHome(){
        List<ItemHome> itemHomes = new ArrayList<>();
        itemHomes.add(new ItemHome(0, "SPECIAL TODAY", mListCoffee));
        itemHomes.add(new ItemHome(1, "TOP FAVOURITE", mListTopFavourite));
        return itemHomes;
    }
}