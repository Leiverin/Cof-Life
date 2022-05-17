package com.project.coffeeapp.webservice;

import com.project.coffeeapp.models.Advertise;
import com.project.coffeeapp.models.Cart;
import com.project.coffeeapp.models.Category;
import com.project.coffeeapp.models.Coffee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetroServiceAPI {
    // Get category
    // [GET] Link API: "https://coflife.000webhostapp.com/CoffeeAPI/GetCategory.php"
    @GET("GetCategory.php")
    Call<List<Category>> getListCategory();

    // Get type of category
    // [GET] Link API: "https://coflife.000webhostapp.com/CoffeeAPI/ItemCategory.php?idCategory"
    @GET("ItemCategory.php")
    Call<List<Coffee>> getItemCategory(
            @Query("idCategory") int idCategory
    );

    // Insert data to cart
    // [POST] Link API: "https://coflife.000webhostapp.com/CoffeeAPI/AddToCart.php"
    @FormUrlEncoded
    @POST("AddToCart.php")
    Call<Cart> addToCart(
            @Field("quantity") int quantity,
            @Field("orderDate") String date,
            @Field("username") String username,
            @Field("idProduct") int id
    );

    // Get data in cart by username
    // [POST] Link API: "https://coflife.000webhostapp.com/CoffeeAPI/GetCart.php"
    @FormUrlEncoded
    @POST("GetCart.php")
    Call<List<Cart>> GetCart(
            @Field("username") String username
    );

    // Handle quantity in cart
    // [POST] Link API: "https://coflife.000webhostapp.com/CoffeeAPI/QuantityInCart.php"
    @FormUrlEncoded
    @POST("QuantityInCart.php")
    Call<Cart> handleQuantity(
            @Field("quantity") int quantity,
            @Field("id") int id
    );

    // Handle delete item in cart
    // [POST] Link API: "https://coflife.000webhostapp.com/CoffeeAPI/DeleteItemCart.php"
    @FormUrlEncoded
    @POST("DeleteItemCart.php")
    Call<List<Cart>> deleteCart(
            @Field("id") int id,
            @Field("username") String username
    );

    // Get data coffee
    // [GET] Link API: "https://coflife.000webhostapp.com/CoffeeAPI/GetCoffee.php";
    @GET("GetCoffee.php")
    Call<List<Coffee>> getListCoffee();

    // Get advertisement
    // [GET] Link API: "https://coflife.000webhostapp.com/CoffeeAPI/GetAdvertise.php";
    @GET("GetAdvertise.php")
    Call<List<Advertise>> getAdvertise();
}
