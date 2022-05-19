package com.project.coffeeapp.views;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.coffeeapp.models.Coffee;
import com.project.coffeeapp.webservice.RetroInstance;
import com.project.coffeeapp.webservice.RetroServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteViewModel extends ViewModel {
    private MutableLiveData<List<Coffee>> mListFavourite;

    public FavouriteViewModel() {
        mListFavourite = new MutableLiveData<>();
    }

    public void callAPIGetListFavourite(String username){
        RetroServiceAPI serviceAPI = RetroInstance.getRetroInstance().create(RetroServiceAPI.class);
        Call<List<Coffee>> call = serviceAPI.getListFavourite(username);
        call.enqueue(new Callback<List<Coffee>>() {
            @Override
            public void onResponse(Call<List<Coffee>> call, Response<List<Coffee>> response) {
                mListFavourite.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Coffee>> call, Throwable t) {
                mListFavourite.postValue(null);
            }
        });
    }

    public void callAPIDeleteFavourite(int id, String username){
        RetroServiceAPI serviceAPI = RetroInstance.getRetroInstance().create(RetroServiceAPI.class);
        Call<List<Coffee>> call = serviceAPI.deleteFavourite(id, username);
        call.enqueue(new Callback<List<Coffee>>() {
            @Override
            public void onResponse(Call<List<Coffee>> call, Response<List<Coffee>> response) {
                mListFavourite.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Coffee>> call, Throwable t) {
                mListFavourite.postValue(null);
            }
        });
    }

    public LiveData<List<Coffee>> getListFavourite(){
        return mListFavourite;
    }
}
