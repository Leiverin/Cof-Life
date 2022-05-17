package com.project.coffeeapp.ui.cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.coffeeapp.adapters.CartAdapter;
import com.project.coffeeapp.databinding.FragmentCartBinding;
import com.project.coffeeapp.interfaces.IOnClickItemCart;
import com.project.coffeeapp.models.Cart;
import com.project.coffeeapp.models.Coffee;
import com.project.coffeeapp.views.DetailActivity;

import java.util.List;

public class CartFragment extends Fragment implements IOnClickItemCart {

    private CartViewModel cartViewModel;
    private FragmentCartBinding binding;
    private RecyclerView rvCart;
    private List<Cart> mListItemCart;
    private CartAdapter adapter;
    private int quantity;
    private double totalPrice;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cartViewModel =
                new ViewModelProvider(this).get(CartViewModel.class);

        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rvCart = binding.rvCart;

        // Set RecycleView Cart
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvCart.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(getContext(), mListItemCart, this);
        rvCart.setAdapter(adapter);

        cartViewModel.getListItemCart().observe(getViewLifecycleOwner(), new Observer<List<Cart>>() {
            @Override
            public void onChanged(@Nullable List<Cart> carts) {
                if (carts != null){
                    mListItemCart = carts;
                    adapter.setListItemCart(carts);
                    handleTotalPrice(mListItemCart);
                }
            }
        });

        cartViewModel.getCartAPI("leiverin");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void OnClickItem(Cart cart) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_CART, cart);
        startActivity(intent);
    }

    @Override
    public void OnClickDelete(Cart cart) {
        cartViewModel.deleteItemCart(cart.getId(), "leiverin");
    }

    @Override
    public void OnClickIncrease(Cart cart, TextView tvQuantity, TextView tvPrice) {
        quantity = cart.getQuantity();
        if(quantity < cart.getTotalQuantity()){
            quantity++;
            cart.setQuantity(quantity);
            tvQuantity.setText(quantity+"");
            tvPrice.setText(cart.getPrice() * quantity + "$");
            handleTotalPrice(mListItemCart);
        }
    }

    @Override
    public void OnClickDecrease(Cart cart, TextView tvQuantity, TextView tvPrice) {
        quantity = cart.getQuantity();
        if(quantity > 1){
            quantity--;
            cart.setQuantity(quantity);
            tvQuantity.setText(quantity+"");
            tvPrice.setText(cart.getPrice() * quantity + "$");
            handleTotalPrice(mListItemCart);
        }
    }


    // Handle save quantity of each item
    @Override
    public void onPause() {
        if(mListItemCart != null){
            for(int i = 0; i< mListItemCart.size(); i++){
                Cart cart = mListItemCart.get(i);
                cartViewModel.handleQuantity(cart.getQuantity(), cart.getId());
            }
        }
        super.onPause();
    }

    @Override
    public void onResume() {

        if(mListItemCart != null){
            cartViewModel.getCartAPI("leiverin");
        }
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void handleTotalPrice(List<Cart> mListItemCart){
        totalPrice = 0;
        for(Cart cart : mListItemCart){
            totalPrice += (cart.getPrice() * cart.getQuantity());
        }
        binding.tvTotalPrice.setText(totalPrice+"$");
    }

}