package com.project.coffeeapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.project.coffeeapp.R;
import com.project.coffeeapp.adapters.ItemCategoryAdapter;
import com.project.coffeeapp.databinding.ActivitySelectedCategoryBinding;
import com.project.coffeeapp.interfaces.IOnClickViewItem;
import com.project.coffeeapp.models.Category;
import com.project.coffeeapp.models.Coffee;
import com.project.coffeeapp.ui.category.CategoryViewModel;

import java.util.List;

public class SelectedCategoryActivity extends AppCompatActivity {
    private ActivitySelectedCategoryBinding binding;
    private RecyclerView rvItemCategory;
    private ItemCategoryAdapter adapter;
    private List<Coffee> mListCoffee;
    private CategoryViewModel categoryViewModel;
    public static final String EXTRA_CATEGORY = "EXTRA_CATEGORY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryViewModel =
                new ViewModelProvider(this).get(CategoryViewModel.class);

        binding = ActivitySelectedCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rvItemCategory = binding.rvCategorySelected;

        // Show Category
        Category category = getIntent().getParcelableExtra(EXTRA_CATEGORY);
        binding.tvTitleCategory.setText(category.getName());
        Glide.with(getApplicationContext()).load(category.getImage()).into(binding.imgCategory);
        // Set layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvItemCategory.setLayoutManager(linearLayoutManager);

        // Set adapter
        adapter = new ItemCategoryAdapter(getApplicationContext(), mListCoffee, new IOnClickViewItem() {
            @Override
            public void onClick(Coffee coffee) {
                Intent intent = new Intent(SelectedCategoryActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_COFFEE, coffee);
                startActivity(intent);
            }
        });
        rvItemCategory.setAdapter(adapter);

        categoryViewModel.getListCoffee().observe(this, new Observer<List<Coffee>>() {
            @Override
            public void onChanged(List<Coffee> coffees) {
                mListCoffee = coffees;
                adapter.setListItemCategory(coffees);
            }
        });

        categoryViewModel.callCoffeeAPI(category);
    }


}