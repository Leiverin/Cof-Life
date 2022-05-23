package com.project.coffeeapp.webservice;

import com.project.coffeeapp.models.Advertise;
import com.project.coffeeapp.models.Cart;
import com.project.coffeeapp.models.Category;
import com.project.coffeeapp.models.Coffee;
import com.project.coffeeapp.models.User;

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

    // Handle favourite
    // [POST] Link API: "https://coflife.000webhostapp.com/CoffeeAPI/AddFavourite.php";
    @FormUrlEncoded
    @POST("AddFavourite.php")
    Call<Coffee> handleAddFavourite(
            @Field("idProduct") int id,
            @Field("username") String username
    );

    // Handle favourite
    // [POST] Link API: "https://coflife.000webhostapp.com/CoffeeAPI/RemoveFavourite.php";
    @FormUrlEncoded
    @POST("RemoveFavourite.php")
    Call<Coffee> handleRemoveFavourite(
            @Field("idProduct") int id,
            @Field("username") String username
    );

    // Get list favourite
    // [POST] Link API: "https://coflife.000webhostapp.com/CoffeeAPI/GetFavourite.php";
    @FormUrlEncoded
    @POST("GetFavourite.php")
    Call<List<Coffee>> getListFavourite(
            @Field("username") String username
    );

    // Get top favourite
    // [GET] Link API: "https://coflife.000webhostapp.com/CoffeeAPI/TopFavourite.php";
    @GET("TopFavourite.php")
    Call<List<Coffee>> getTopFavourite();

    // Delete favourite
    // [POST] Link API: "https://coflife.000webhostapp.com/CoffeeAPI/DeleteFavourite.php";
    @FormUrlEncoded
    @POST("DeleteFavourite.php")
    Call<List<Coffee>> deleteFavourite(
            @Field("idProduct") int id,
            @Field("username") String username
    );

    // Get user current
    // [POST] Link API: "https://coflife.000webhostapp.com/CoffeeAPI/GetUser.php";
    @FormUrlEncoded
    @POST("GetUser.php")
    Call<User> getUserCurrent(
            @Field("account") String account,
            @Field("password") String password
    );

    // Create account user
    // [POST] Link API: "https://coflife.000webhostapp.com/CoffeeAPI/CreateUser.php"
    @FormUrlEncoded
    @POST("CreateUser.php")
    Call<User> createUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("fullName") String fullName
    );
}
