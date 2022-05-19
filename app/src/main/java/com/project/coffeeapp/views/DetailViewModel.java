package com.project.coffeeapp.views;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

public class DetailViewModel extends ViewModel {
    private MutableLiveData<Cart> mCartLiveData;
    private MutableLiveData<List<Coffee>> mListCoffeeLiveData;

    public DetailViewModel(){
        mCartLiveData = new MutableLiveData<>();
        mListCoffeeLiveData = new MutableLiveData<>();
    }

    public void postDataAPI(Context context, Cart cart){
        RetroServiceAPI serviceAPI = RetroInstance.getRetroInstance().create(RetroServiceAPI.class);
        Call<Cart> call = serviceAPI.addToCart(cart.getQuantity(), cart.getOrderDate(), cart.getUsername(), cart.getIdProduct());
        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context, "Exceeding the quantity of the item", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("Zzzz", "onResponse: ");
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {

            }
        });
    }

    // Handling when products move from cart to detail
    public void handleQuantityFromCart(int quantity, int id){
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

    // Handle favourite
    public void handleAddFavourite(int id, String username){
        RetroServiceAPI serviceAPI = RetroInstance.getRetroInstance().create(RetroServiceAPI.class);
        Call<Coffee> call = serviceAPI.handleAddFavourite(id, username);
        call.enqueue(new Callback<Coffee>() {
            @Override
            public void onResponse(Call<Coffee> call, Response<Coffee> response) {

            }

            @Override
            public void onFailure(Call<Coffee> call, Throwable t) {

            }
        });
    }

    // Handle favourite
    public void handleRemoveFavourite(int id, String username){
        RetroServiceAPI serviceAPI = RetroInstance.getRetroInstance().create(RetroServiceAPI.class);
        Call<Coffee> call = serviceAPI.handleRemoveFavourite(id, username);
        call.enqueue(new Callback<Coffee>() {
            @Override
            public void onResponse(Call<Coffee> call, Response<Coffee> response) {

            }

            @Override
            public void onFailure(Call<Coffee> call, Throwable t) {

            }
        });
    }

    public void callAPIListFavourite(String username){
        RetroServiceAPI serviceAPI = RetroInstance.getRetroInstance().create(RetroServiceAPI.class);
        Call<List<Coffee>> call = serviceAPI.getListFavourite(username);
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

    public LiveData<Cart> getCart(){
        return mCartLiveData;
    }
    public LiveData<List<Coffee>> getListFavourite(){
        return mListCoffeeLiveData;
    }

}
