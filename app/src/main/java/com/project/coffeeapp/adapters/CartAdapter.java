package com.project.coffeeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.coffeeapp.R;
import com.project.coffeeapp.interfaces.IOnClickItemCart;
import com.project.coffeeapp.interfaces.IOnClickViewItem;
import com.project.coffeeapp.models.Cart;
import com.project.coffeeapp.models.Coffee;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context mContext;
    private List<Cart> mListItemCart;
    private IOnClickItemCart onClickItemCart;

    public CartAdapter(Context mContext, List<Cart> mListItemCart, IOnClickItemCart onClickItemCart) {
        this.mContext = mContext;
        this.mListItemCart = mListItemCart;
        this.onClickItemCart = onClickItemCart;
    }

    public void setListItemCart(List<Cart> mListItemCart){
        this.mListItemCart = mListItemCart;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartAdapter.CartViewHolder holder, int position) {
        Cart cart = mListItemCart.get(position);
        if(cart == null){
            return;
        }else{
            holder.tvName.setText(cart.getName());
            holder.tvDescription.setText(cart.getDescription());
            holder.tvQuantity.setText(cart.getQuantity()+"");
            double price = cart.getPrice() * Integer.parseInt(holder.tvQuantity.getText().toString());
            holder.tvPrice.setText(price+"$");
            Glide.with(mContext).load(cart.getImage()).into(holder.imgCoffee);
            holder.btnDecrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItemCart.OnClickDecrease(cart, holder.tvQuantity, holder.tvPrice);
                }
            });
            holder.btnIncrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItemCart.OnClickIncrease(cart, holder.tvQuantity, holder.tvPrice);
                }
            });
            holder.imbDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItemCart.OnClickDelete(cart);
                }
            });
            holder.viewCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItemCart.OnClickItem(cart);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(mListItemCart != null){
            return mListItemCart.size();
        }
        return 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout viewCart;
        private ImageView imgCoffee;
        private TextView tvName;
        private TextView tvDescription;
        private TextView tvPrice;
        private Button btnDecrease;
        private TextView tvQuantity;
        private Button btnIncrease;
        private ImageButton imbDelete;

        public CartViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View view) {
            viewCart = (LinearLayout) view.findViewById(R.id.view_Cart);
            imgCoffee = (ImageView) view.findViewById(R.id.img_Coffee);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvDescription = (TextView) view.findViewById(R.id.tv_description);
            tvPrice = (TextView) view.findViewById(R.id.tv_price);
            btnDecrease = (Button) view.findViewById(R.id.btn_decrease);
            tvQuantity = (TextView) view.findViewById(R.id.tv_quantity);
            btnIncrease = (Button) view.findViewById(R.id.btn_increase);
            imbDelete = (ImageButton) view.findViewById(R.id.imb_delete);
        }
    }
}
