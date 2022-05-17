package com.project.coffeeapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.coffeeapp.models.Advertise;
import com.project.coffeeapp.models.Coffee;
import com.project.coffeeapp.webservice.RetroInstance;
import com.project.coffeeapp.webservice.RetroServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Coffee>> mListCoffeeLiveData;
    private MutableLiveData<List<Advertise>> mListAdvertiseLiveData;

    public HomeViewModel() {
        mListCoffeeLiveData = new MutableLiveData<>();
        mListAdvertiseLiveData = new MutableLiveData<>();
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

    public LiveData<List<Coffee>> getCoffee() {
        return mListCoffeeLiveData;
    }

    public LiveData<List<Advertise>> getAdvertise(){
        return mListAdvertiseLiveData;
    }
}