package com.project.coffeeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.project.coffeeapp.R;
import com.project.coffeeapp.models.Advertise;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;


public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderViewHolder> {
    private Context mContext;
    private List<Advertise> mListAdvertise;

    public SliderAdapter(Context mContext, List<Advertise> mListAdvertise) {
        this.mContext = mContext;
        this.mListAdvertise = mListAdvertise;
    }

    public void setListAdvertise(List<Advertise> mListAdvertise){
        this.mListAdvertise = mListAdvertise;
        notifyDataSetChanged();
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder holder, int position) {
        Advertise advertise = mListAdvertise.get(position);
        if(advertise == null){
            return;
        }else{
            Glide.with(mContext).load(advertise.getImage()).into(holder.imgSlider);
        }
    }

    @Override
    public int getCount() {
        if(mListAdvertise != null){
            return mListAdvertise.size();
        }
        return 0;
    }

    public class SliderViewHolder extends SliderViewAdapter.ViewHolder {

        private ShapeableImageView imgSlider;

        public SliderViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View view) {
            imgSlider = (ShapeableImageView) view.findViewById(R.id.img_slider);
        }
    }
}
