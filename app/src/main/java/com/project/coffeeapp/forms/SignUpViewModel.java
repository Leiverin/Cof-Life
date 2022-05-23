package com.project.coffeeapp.forms;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.project.coffeeapp.models.User;
import com.project.coffeeapp.webservice.RetroInstance;
import com.project.coffeeapp.webservice.RetroServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {
    private MutableLiveData<User> mUserLiveData;

    public SignUpViewModel() {
        mUserLiveData = new MutableLiveData<>();
    }

    public void callAPICreateAccount(Context mContext, User user){
        RetroServiceAPI serviceAPI = RetroInstance.getRetroInstance().create(RetroServiceAPI.class);
        Call<User> call = serviceAPI.createUser(user.getUsername(), user.getPassword(), user.getFullName());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    mUserLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(mContext, "Username already exist", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public LiveData<User> getUser(){
        return mUserLiveData;
    }
}
