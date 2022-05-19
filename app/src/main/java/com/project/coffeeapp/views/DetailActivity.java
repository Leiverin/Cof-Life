package com.project.coffeeapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.bumptech.glide.Glide;
import com.project.coffeeapp.databinding.ActivityDetailBinding;
import com.project.coffeeapp.models.Cart;
import com.project.coffeeapp.models.Coffee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_COFFEE = "EXTRA_COFFEE";
    public static final String EXTRA_CART = "EXTRA_CART";
    private ActivityDetailBinding binding;
    private int quantity;
    private double price;
    private double totalPrice;
    private DetailViewModel detailViewModel;
    private int totalQuantity;
    private int idProduct;
    private List<Coffee> mListCoffee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set view from coffee
        Coffee coffee = getIntent().getParcelableExtra(EXTRA_COFFEE);
        if(coffee != null){
            binding.tvTitle.setText(coffee.getName());
            binding.tvDescription.setText(coffee.getDescription());
            binding.tvPrice.setText(coffee.getPrice()+"$");
            binding.tvTotal.setText("Total: "+coffee.getTotal());
            totalQuantity = coffee.getTotal();
            price = coffee.getPrice();
            binding.tvQuantity.setText(1+"");
            Glide.with(getApplicationContext()).load(coffee.getImage()).into(binding.imgCoffee);
            binding.tvStatus.setVisibility(View.GONE);
            idProduct = coffee.getId();
        }

        // Set view from cart
        Cart cart = getIntent().getParcelableExtra(EXTRA_CART);
        if(cart != null){
            binding.tvTitle.setText(cart.getName());
            binding.tvDescription.setText(cart.getDescription());
            binding.tvPrice.setText(cart.getPrice()+"$");
            binding.tvTotal.setText("Total: "+cart.getTotalQuantity());
            totalQuantity = cart.getTotalQuantity();
            price = cart.getPrice();
            binding.tvQuantity.setText(cart.getQuantity()+"");
            Glide.with(getApplicationContext()).load(cart.getImage()).into(binding.imgCoffee);
            binding.tvStatus.setVisibility(View.GONE);
            idProduct = cart.getIdProduct();
        }

        quantity = Integer.parseInt(binding.tvQuantity.getText().toString());

        binding.btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DecreaseQuantity();
            }
        });

        binding.btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IncreaseQuantity();
            }
        });

        binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddToCart(coffee, cart);
            }
        });

        detailViewModel.getListFavourite().observe(this, new Observer<List<Coffee>>() {
            @Override
            public void onChanged(List<Coffee> coffees) {
                mListCoffee = coffees;
                for(Coffee c : mListCoffee){
                    if(c.getId() == idProduct){
                        binding.btnFav.setChecked(true);
                        break;
                    }
                }
            }
        });

        detailViewModel.callAPIListFavourite("leiverin");

        binding.btnFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                HandleFavButton(b, coffee, cart);
            }
        });
    }

    private void HandleFavButton(boolean b, Coffee coffee, Cart cart) {
        if(b){
            if(cart != null){
                detailViewModel.handleAddFavourite(cart.getId(), "leiverin");
            }
            if(coffee != null){
                detailViewModel.handleAddFavourite(coffee.getId(), "leiverin");
            }
        }else{
            if(cart != null){
                detailViewModel.handleRemoveFavourite(cart.getId(), "leiverin");
            }
            if(coffee != null){
                detailViewModel.handleRemoveFavourite(coffee.getId(), "leiverin");
            }
        }
    }

    private void AddToCart(Coffee coffee, Cart cart) {
        quantity = Integer.parseInt(binding.tvQuantity.getText().toString());
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime());
        if(coffee != null){
            detailViewModel.postDataAPI(
                DetailActivity.this,
                new Cart(quantity, date, "leiverin", coffee.getId())
            );

        }
        if(cart != null){
            detailViewModel.handleQuantityFromCart(quantity, cart.getId());
        }

    }

    private void IncreaseQuantity() {

        if(quantity < totalQuantity){
            quantity++;
            totalPrice = price * quantity;
            binding.tvQuantity.setText(quantity+"");
            binding.tvPrice.setText(totalPrice+"$");
            if(quantity == totalQuantity){
                binding.tvStatus.setVisibility(View.VISIBLE);
            }
        }
    }

    private void DecreaseQuantity() {
        if(quantity > 1){
            quantity --;
            totalPrice = price * quantity;
            binding.tvQuantity.setText(quantity+"");
            binding.tvPrice.setText(totalPrice+"$");
            binding.tvStatus.setVisibility(View.GONE);
        }
    }
}