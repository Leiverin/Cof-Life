package com.project.coffeeapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.project.coffeeapp.adapters.FavouriteAdapter;
import com.project.coffeeapp.databinding.ActivityFavouriteBinding;
import com.project.coffeeapp.interfaces.IOnClickDeleteItem;
import com.project.coffeeapp.interfaces.IOnClickViewItem;
import com.project.coffeeapp.models.Coffee;

import java.util.List;

public class FavouriteActivity extends AppCompatActivity {
    private ActivityFavouriteBinding binding;
    private FavouriteViewModel favouriteViewModel;
    private List<Coffee> mListFavourite;
    private RecyclerView rvFavourite;
    private FavouriteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favouriteViewModel =
                new ViewModelProvider(this).get(FavouriteViewModel.class);
        binding = ActivityFavouriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        rvFavourite = binding.rvFavourite;

        rvFavourite.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new FavouriteAdapter(this, mListFavourite, new IOnClickViewItem() {
            @Override
            public void onClick(Coffee coffee) {
                Intent intent = new Intent(FavouriteActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_COFFEE, coffee);
                startActivity(intent);
            }
        }, new IOnClickDeleteItem() {
            @Override
            public void onDelete(Coffee coffee) {
                favouriteViewModel.callAPIDeleteFavourite(coffee.getId(), "leiverin");
            }
        });
        rvFavourite.setAdapter(adapter);

        favouriteViewModel.getListFavourite().observe(this, new Observer<List<Coffee>>() {
            @Override
            public void onChanged(List<Coffee> coffees) {
                if(coffees != null){
                    mListFavourite = coffees;
                    adapter.setListFavourite(coffees);
                }
            }
        });

        favouriteViewModel.callAPIGetListFavourite("leiverin");
    }
}