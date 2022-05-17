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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends ViewModel {
    private MutableLiveData<Cart> mCartLiveData;
    public DetailViewModel(){
        mCartLiveData = new MutableLiveData<>();
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


    public LiveData<Cart> getCart(){
        return mCartLiveData;
    }
}
