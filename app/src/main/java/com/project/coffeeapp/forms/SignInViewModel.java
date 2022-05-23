package com.project.coffeeapp.forms;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.coffeeapp.models.User;
import com.project.coffeeapp.webservice.RetroInstance;
import com.project.coffeeapp.webservice.RetroServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInViewModel extends ViewModel {

    private MutableLiveData<User> userLiveData;

    public SignInViewModel() {
        userLiveData = new MutableLiveData<>();
    }

    public void callAPIUser(String account, String password){
        RetroServiceAPI serviceAPI = RetroInstance.getRetroInstance().create(RetroServiceAPI.class);
        Call<User> call = serviceAPI.getUserCurrent(account, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    userLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                userLiveData.postValue(null);
            }
        });
    }

    public LiveData<User> getUser(){
        return userLiveData;
    }
}
