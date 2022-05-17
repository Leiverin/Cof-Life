package com.project.coffeeapp.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {
    // Lớp để định nghĩa URL EndPoint cho các hoạt động liên quan tới http
    public static final String BASE_URL = "https://coflife.000webhostapp.com/CoffeeAPI/";
    private static Retrofit retrofit;

    public static Retrofit getRetroInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
