package com.project.coffeeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.coffeeapp.R;
import com.project.coffeeapp.interfaces.IOnClickViewCategory;
import com.project.coffeeapp.models.Category;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context context;
    private List<Category> mListCategory;
    private IOnClickViewCategory onClickView;

    public CategoryAdapter(Context context, List<Category> mListCategory, IOnClickViewCategory onClickView) {
        this.context = context;
        this.mListCategory = mListCategory;
        this.onClickView = onClickView;
    }

    public void setListCategory(List<Category> mListCategory){
        this.mListCategory = mListCategory;
        notifyDataSetChanged();
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        Category category = mListCategory.get(position);
        if(category == null){
            return;
        }else{
            holder.tvTitle.setText(category.getName());
            Glide.with(context).load(category.getImage()).into(holder.imgCategory);
            holder.viewCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickView.OnClick(category);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(mListCategory != null){
            return mListCategory.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ImageView imgCategory;
        private LinearLayout viewCategory;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View view) {
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            imgCategory = (ImageView) view.findViewById(R.id.img_Category);
            viewCategory = (LinearLayout) view.findViewById(R.id.view_category);
        }
    }
}
