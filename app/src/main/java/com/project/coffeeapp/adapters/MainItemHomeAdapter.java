package com.project.coffeeapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.coffeeapp.R;
import com.project.coffeeapp.interfaces.IOnClickViewItem;
import com.project.coffeeapp.models.Coffee;
import com.project.coffeeapp.models.ItemHome;

import java.util.List;

public class MainItemHomeAdapter extends RecyclerView.Adapter<MainItemHomeAdapter.MainItemHomeViewHolder> {
    private Context mContext;
    private List<ItemHome> mListItemHome;
    private IOnClickViewItem onClickViewItem;

    public MainItemHomeAdapter(Context mContext, List<ItemHome> mListItemHome, IOnClickViewItem onClickViewItem) {
        this.mContext = mContext;
        this.mListItemHome = mListItemHome;
        this.onClickViewItem = onClickViewItem;
        notifyDataSetChanged();
    }

    @Override
    public MainItemHomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_parent, parent, false);
        return new MainItemHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainItemHomeAdapter.MainItemHomeViewHolder holder, int position) {
        ItemHome itemHome = mListItemHome.get(position);
        if(itemHome == null){
            return;
        }else{
            holder.tvTitle.setText(itemHome.getTitle());
            holder.rvChild.setAdapter(new ChildItemAdapter(mContext, itemHome.getListCoffee(), new IOnClickViewItem() {
                @Override
                public void onClick(Coffee coffee) {
                    onClickViewItem.onClick(coffee);
                }
            }));
            holder.rvChild.setLayoutManager(new GridLayoutManager(mContext, 2));
        }
    }

    @Override
    public int getItemCount() {
        if(mListItemHome != null){
            return mListItemHome.size();
        }
        return 0;
    }

    public class MainItemHomeViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ImageButton imbDetail;
        private RecyclerView rvChild;

        public MainItemHomeViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View view) {
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            imbDetail = (ImageButton) view.findViewById(R.id.imb_detail);
            rvChild = (RecyclerView) view.findViewById(R.id.rv_child);
        }
    }
}
