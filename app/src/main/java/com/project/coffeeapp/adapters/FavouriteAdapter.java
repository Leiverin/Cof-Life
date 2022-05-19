package com.project.coffeeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.coffeeapp.R;
import com.project.coffeeapp.interfaces.IOnClickDeleteItem;
import com.project.coffeeapp.interfaces.IOnClickViewItem;
import com.project.coffeeapp.models.Coffee;

import java.util.List;


public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {
    private Context mContext;
    private List<Coffee> mListFavourite;
    private IOnClickViewItem onClickViewItem;
    private IOnClickDeleteItem onClickDeleteItem;

    public FavouriteAdapter(Context mContext, List<Coffee> mListFavourite, IOnClickViewItem onClickViewItem, IOnClickDeleteItem onClickDeleteItem) {
        this.mContext = mContext;
        this.mListFavourite = mListFavourite;
        this.onClickViewItem = onClickViewItem;
        this.onClickDeleteItem = onClickDeleteItem;
    }

    public void setListFavourite(List<Coffee> mListFavourite){
        this.mListFavourite = mListFavourite;
        notifyDataSetChanged();
    }

    @Override
    public FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favourite, parent, false);
        return new FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavouriteViewHolder holder, int position) {
        Coffee coffee = mListFavourite.get(position);
        if(coffee == null){
            return;
        }else{
            Glide.with(mContext).load(coffee.getImage()).into(holder.imgCoffee);
            holder.tvName.setText(coffee.getName());
            holder.tvDescription.setText(coffee.getDescription());
            holder.imbDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickDeleteItem.onDelete(coffee);
                }
            });
            holder.viewFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickViewItem.onClick(coffee);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(mListFavourite != null){
            return mListFavourite.size();
        }
        return 0;
    }

    public class FavouriteViewHolder extends RecyclerView.ViewHolder {

        private CardView viewFavourite;
        private ImageView imgCoffee;
        private ImageButton imbDelete;
        private TextView tvName;
        private TextView tvDescription;

        public FavouriteViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View view) {
            viewFavourite = (CardView) view.findViewById(R.id.view_favourite);
            imgCoffee = (ImageView) view.findViewById(R.id.img_Coffee);
            imbDelete = (ImageButton) view.findViewById(R.id.imb_delete);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvDescription = (TextView) view.findViewById(R.id.tv_description);
        }
    }
}
