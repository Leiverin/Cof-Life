package com.project.coffeeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.coffeeapp.R;
import com.project.coffeeapp.interfaces.IOnClickViewItem;
import com.project.coffeeapp.models.Coffee;

import java.util.List;

public class ChildItemAdapter extends RecyclerView.Adapter<ChildItemAdapter.ChildItemViewHolder> {
    private Context mContext;
    private List<Coffee> mListCoffee;
    private IOnClickViewItem onClickViewItem;

    public ChildItemAdapter(Context mContext, List<Coffee> mListCoffee, IOnClickViewItem onClickViewItem) {
        this.mContext = mContext;
        this.mListCoffee = mListCoffee;
        this.onClickViewItem = onClickViewItem;
        notifyDataSetChanged();
    }

    @Override
    public ChildItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_child, parent, false);
        return new ChildItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChildItemAdapter.ChildItemViewHolder holder, int position) {
        Coffee coffee = mListCoffee.get(position);
        if(coffee == null){
            return;
        }else{
            holder.tvTitle.setText(coffee.getName());
            Glide.with(mContext).load(coffee.getImage()).into(holder.imgCategory);
            holder.viewCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickViewItem.onClick(coffee);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int limit = 4;
        if(mListCoffee != null){
            return limit;
        }
        return 0;
    }

    public class ChildItemViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout viewCategory;
        private ImageView imgCategory;
        private TextView tvTitle;

        public ChildItemViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View view) {
            viewCategory = (LinearLayout) view.findViewById(R.id.view_category);
            imgCategory = (ImageView) view.findViewById(R.id.img_Category);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
        }
    }
}
