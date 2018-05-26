package com.xema.shopmanager.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.xema.shopmanager.R;
import com.xema.shopmanager.model.OrderMenu;

import java.util.ArrayList;

public class OrderMenuAdapter extends RecyclerView.Adapter<OrderMenuAdapter.ViewHolder> {
    private ArrayList<OrderMenu> list;
    private RequestManager requestManager;
    private onMenuClickListener onMenuClickListener;

    public interface onMenuClickListener
    {
        public void onClick(View view, int position);
    }
    public  OrderMenuAdapter(ArrayList<OrderMenu> list, RequestManager requestManager, onMenuClickListener onMenuClickListener)
    {
        this.list = list;
        this.requestManager = requestManager;
        this.onMenuClickListener = onMenuClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_menu, parent, false);
        OrderMenuAdapter.ViewHolder viewHolder = new OrderMenuAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txt_category.setText(list.get(position).getCategory());
        holder.txt_name.setText(list.get(position).getName());
        holder.txt_price.setText(list.get(position).getPrice());
        requestManager.load(list.get(position).getIm_url()).into(holder.im_menu);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMenuClickListener.onClick(v,position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView im_menu;
        public TextView txt_category;
        public TextView txt_name;
        public TextView txt_price;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            im_menu = (ImageView)itemView.findViewById(R.id.menu_im_menu);
            txt_category = (TextView)itemView.findViewById(R.id.menu_txt_category);
            txt_name = (TextView)itemView.findViewById(R.id.menu_txt_name);
            txt_price = (TextView)itemView.findViewById(R.id.menu_txt_price);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.menu_layout);

        }

    }
}
