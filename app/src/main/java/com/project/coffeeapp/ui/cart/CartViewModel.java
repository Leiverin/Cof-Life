package com.project.coffeeapp.ui.cart;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.project.coffeeapp.models.Cart;
import com.project.coffeeapp.models.Coffee;
import com.project.coffeeapp.webservice.RetroInstance;
import com.project.coffeeapp.webservice.RetroServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartViewModel extends ViewModel {

    private MutableLiveData<List<Cart>> mListItemCartLiveData;

    public CartViewModel() {
        mListItemCartLiveData = new MutableLiveData<>();
    }

    public void getCartAPI(String username){
        RetroServiceAPI serviceAPI = RetroInstance.getRetroInstance().create(RetroServiceAPI.class);
        Call<List<Cart>> call = serviceAPI.GetCart(username);
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                mListItemCartLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                mListItemCartLiveData.postValue(null);
            }
        });
    }

    public void handleQuantity(int quantity, int id){
        RetroServiceAPI serviceAPI = RetroInstance.getRetroInstance().create(RetroServiceAPI.class);
        Call<Cart> call = serviceAPI.handleQuantity(quantity, id);
        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {

            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {

            }
        });
    }

    public void deleteItemCart(int id, String username){
        RetroServiceAPI serviceAPI = RetroInstance.getRetroInstance().create(RetroServiceAPI.class);
        Call<List<Cart>> call = serviceAPI.deleteCart(id, username);
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                mListItemCartLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

            }
        });
    }

    public LiveData<List<Cart>> getListItemCart() {
        return mListItemCartLiveData;
    }
}