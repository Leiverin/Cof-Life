package com.project.coffeeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.coffeeapp.R;
import com.project.coffeeapp.interfaces.IOnClickViewItem;
import com.project.coffeeapp.models.Coffee;

import java.util.List;


public class ItemCategoryAdapter extends RecyclerView.Adapter<ItemCategoryAdapter.CategoryItemViewHolder> {
    private Context context;
    private List<Coffee> mListCoffee;
    private IOnClickViewItem onClickView;

    public ItemCategoryAdapter(Context context, List<Coffee> mListCoffee, IOnClickViewItem onClickView) {
        this.context = context;
        this.mListCoffee = mListCoffee;
        this.onClickView = onClickView;
    }

    public void setListItemCategory(List<Coffee> mListCoffee) {
        this.mListCoffee = mListCoffee;
        notifyDataSetChanged();
    }

    @Override
    public CategoryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_selected, parent, false);
        return new CategoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryItemViewHolder holder, int position) {
        Coffee coffee = mListCoffee.get(position);
        if (coffee == null) {
            return;
        } else {
            Glide.with(context).load(coffee.getImage()).into(holder.imgCoffee);
            holder.tvName.setText(coffee.getName());
            holder.tvDescription.setText(coffee.getDescription());
            holder.tvPrice.setText(coffee.getPrice()+"$");
            holder.btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickView.onClick(coffee);
                }
            });
            holder.imgCoffee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickView.onClick(coffee);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mListCoffee != null) {
            return mListCoffee.size();
        }
        return 0;
    }

    public class CategoryItemViewHolder extends RecyclerView.ViewHolder {

        private CoordinatorLayout viewCategorySelected;
        private ImageView imgCoffee;
        private TextView tvName;
        private TextView tvPrice;
        private TextView tvDescription;
        private Button btnDetail;

        public CategoryItemViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            viewCategorySelected = (CoordinatorLayout) itemView.findViewById(R.id.view_category_selected);
            imgCoffee = (ImageView) itemView.findViewById(R.id.img_Coffee);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
            btnDetail = (Button) itemView.findViewById(R.id.btn_detail);
        }


    }
}
