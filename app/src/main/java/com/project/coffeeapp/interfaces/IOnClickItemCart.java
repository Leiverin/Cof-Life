package com.project.coffeeapp.interfaces;

import android.widget.TextView;

import com.project.coffeeapp.models.Cart;
import com.project.coffeeapp.models.Coffee;

public interface IOnClickItemCart {
    void OnClickItem(Cart cart);
    void OnClickDelete(Cart cart);
    void OnClickIncrease(Cart cart, TextView tvQuantity, TextView tvPrice);
    void OnClickDecrease(Cart cart, TextView tvQuantity, TextView tvPrice);
}
