package com.project.coffeeapp.ui.category;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.project.coffeeapp.models.Category;
import com.project.coffeeapp.models.Coffee;
import com.project.coffeeapp.webservice.RetroInstance;
import com.project.coffeeapp.webservice.RetroServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryViewModel extends ViewModel {

    private MutableLiveData<List<Category>> mListCategoryLiveData;
    private MutableLiveData<List<Coffee>> mListCoffeeLiveData;

    public CategoryViewModel() {
        mListCategoryLiveData = new MutableLiveData<>();
        mListCoffeeLiveData = new MutableLiveData<>();
    }

    public void callAPI(){
        RetroServiceAPI serviceAPI = RetroInstance.getRetroInstance().create(RetroServiceAPI.class);
        Call<List<Category>> call = serviceAPI.getListCategory();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                mListCategoryLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                mListCategoryLiveData.postValue(null);
            }
        });
    }

    public void callCoffeeAPI(Category category){
        RetroServiceAPI serviceAPI = RetroInstance.getRetroInstance().create(RetroServiceAPI.class);
        Call<List<Coffee>> call = serviceAPI.getItemCategory(category.getId());
        call.enqueue(new Callback<List<Coffee>>() {
            @Override
            public void onResponse(Call<List<Coffee>> call, Response<List<Coffee>> response) {
                mListCoffeeLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Coffee>> call, Throwable t) {
                mListCoffeeLiveData.postValue(null);
            }
        });
    }

    public LiveData<List<Category>> getListCategory() {
        return mListCategoryLiveData;
    }
    public LiveData<List<Coffee>> getListCoffee() {
        return mListCoffeeLiveData;
    }
}