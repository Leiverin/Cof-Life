package com.project.coffeeapp.ui.category;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.project.coffeeapp.adapters.CategoryAdapter;
import com.project.coffeeapp.databinding.FragmentCategoryBinding;
import com.project.coffeeapp.interfaces.IOnClickViewCategory;
import com.project.coffeeapp.models.Category;
import com.project.coffeeapp.views.SelectedCategoryActivity;

import java.util.List;

public class CategoryFragment extends Fragment {

    private CategoryViewModel categoryViewModel;
    private FragmentCategoryBinding binding;
    private List<Category> mListCategory;
    private CategoryAdapter adapter;
    private RecyclerView rvCategory;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoryViewModel =
                new ViewModelProvider(this).get(CategoryViewModel.class);

        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        rvCategory = binding.rvCategory;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        rvCategory.setLayoutManager(gridLayoutManager);
        adapter = new CategoryAdapter(getContext(), mListCategory, new IOnClickViewCategory() {
            @Override
            public void OnClick(Category category) {
                Intent intent = new Intent(getActivity(), SelectedCategoryActivity.class);
                intent.putExtra(SelectedCategoryActivity.EXTRA_CATEGORY, category);
                startActivity(intent);
            }
        });
        rvCategory.setAdapter(adapter);

        categoryViewModel.getListCategory().observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                if(categories != null){
                    mListCategory = categories;
                    adapter.setListCategory(categories);
                }
            }
        });
        categoryViewModel.callAPI();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}