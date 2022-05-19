package com.project.coffeeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.project.coffeeapp.R;
import com.project.coffeeapp.interfaces.IOnClickViewCategory;
import com.project.coffeeapp.models.Category;

import java.util.List;


public class CategoryHomeAdapter extends RecyclerView.Adapter<CategoryHomeAdapter.CategoryHomeViewHolder> {
    private Context mContext;
    private List<Category> mListCategory;
    private IOnClickViewCategory onClickViewCategory;

    public CategoryHomeAdapter(Context mContext, List<Category> mListCategory, IOnClickViewCategory onClickViewCategory) {
        this.mContext = mContext;
        this.mListCategory = mListCategory;
        this.onClickViewCategory = onClickViewCategory;
    }

    public void setListCategory(List<Category> mListCategory){
        this.mListCategory = mListCategory;
        notifyDataSetChanged();
    }

    @Override
    public CategoryHomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_home, parent, false);
        return new CategoryHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryHomeViewHolder holder, int position) {
        Category category = mListCategory.get(position);
        if(category == null){
            return;
        }else{
            holder.tvCategoryTitle.setText(category.getName());
            holder.viewCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickViewCategory.OnClick(category);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mListCategory != null) {
            return mListCategory.size();
        }
        return 0;
    }

    public class CategoryHomeViewHolder extends RecyclerView.ViewHolder {

        private CardView viewCategory;
        private TextView tvCategoryTitle;

        public CategoryHomeViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View view) {
            viewCategory = (CardView) view.findViewById(R.id.view_category);
            tvCategoryTitle = (TextView) view.findViewById(R.id.tv_category_title);
        }
    }
}
