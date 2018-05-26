package com.xema.shopmanager.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.xema.shopmanager.R;
import com.xema.shopmanager.model.OrderCategory;

import java.util.ArrayList;

public class OrderCategoryAdapter extends RecyclerView.Adapter<OrderCategoryAdapter.ViewHolder> {
    private ArrayList<OrderCategory> list;
    private RequestManager requestManager;
    private onCategoryClickListener onCategoryClickListener;

    public OrderCategoryAdapter(ArrayList<OrderCategory> list, RequestManager requestManager)
    {
        this.list = list;
        this.requestManager = requestManager;
    }

    public interface onCategoryClickListener
    {
        public void onClick(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_category, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_category.setText(list.get(position).getCategory_name());
        requestManager.load(list.get(position).getCategory_image()).into(holder.im_category);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public ImageView im_category;
        public TextView txt_category;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            im_category = (ImageView)itemView.findViewById(R.id.order_im_category);
            txt_category = (TextView)itemView.findViewById(R.id.order_txt_category);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
