package com.project.coffeeapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.coffeeapp.models.Advertise;
import com.project.coffeeapp.models.Category;
import com.project.coffeeapp.models.Coffee;
import com.project.coffeeapp.webservice.RetroInstance;
import com.project.coffeeapp.webservice.RetroServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Coffee>> mListCoffeeLiveData;
    private MutableLiveData<List<Coffee>> mListTopFavouriteLiveData;
    private MutableLiveData<List<Advertise>> mListAdvertiseLiveData;
    private MutableLiveData<List<Category>> mListCategoryLiveData;

    public HomeViewModel() {
        mListCoffeeLiveData = new MutableLiveData<>();
        mListAdvertiseLiveData = new MutableLiveData<>();
        mListCategoryLiveData = new MutableLiveData<>();
        mListTopFavouriteLiveData = new MutableLiveData<>();
    }

    public void callAPIGetCoffee(){
        RetroServiceAPI serviceAPI = RetroInstance.getRetroInstance().create(RetroServiceAPI.class);
        Call<List<Coffee>> call = serviceAPI.getListCoffee();
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

    public void callAPIGetAdvertise(){
        RetroServiceAPI serviceAPI = RetroInstance.getRetroInstance().create(RetroServiceAPI.class);
        Call<List<Advertise>> call = serviceAPI.getAdvertise();
        call.enqueue(new Callback<List<Advertise>>() {
            @Override
            public void onResponse(Call<List<Advertise>> call, Response<List<Advertise>> response) {
                mListAdvertiseLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Advertise>> call, Throwable t) {
                mListAdvertiseLiveData.postValue(null);
            }
        });
    }

    public void callAPIGetCategory(){
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

    public void callAPITopFavourite(){
        RetroServiceAPI serviceAPI = RetroInstance.getRetroInstance().create(RetroServiceAPI.class);
        Call<List<Coffee>> call = serviceAPI.getTopFavourite();
        call.enqueue(new Callback<List<Coffee>>() {
            @Override
            public void onResponse(Call<List<Coffee>> call, Response<List<Coffee>> response) {
                mListTopFavouriteLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Coffee>> call, Throwable t) {
                mListTopFavouriteLiveData.postValue(null);
            }
        });
    }

    public LiveData<List<Coffee>> getCoffee() {
        return mListCoffeeLiveData;
    }
    public LiveData<List<Advertise>> getAdvertise(){
        return mListAdvertiseLiveData;
    }
    public LiveData<List<Category>> getCategory(){
        return mListCategoryLiveData;
    }
    public LiveData<List<Coffee>> getTopFavourite(){
        return mListTopFavouriteLiveData;
    }

}