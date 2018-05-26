package com.xema.shopmanager.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xema.shopmanager.R;
import com.xema.shopmanager.model.BuyList;

import java.util.ArrayList;

public class BuyListAdapter extends RecyclerView.Adapter<BuyListAdapter.ViewHolder>{
    private ArrayList<BuyList> list;
    private onClickListener onClickListener;

    public interface onClickListener
    {
        public void onClick(View view, int position);
    }
    public  BuyListAdapter(ArrayList<BuyList> list, onClickListener onClickListener)
    {
        this.onClickListener = onClickListener;
        this.list = list;
    }

    @NonNull
    @Override
    public BuyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buy_list, parent, false);
        BuyListAdapter.ViewHolder viewHolder = new BuyListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BuyListAdapter.ViewHolder holder, int position) {
        holder.txt_date.setText(list.get(position).getDate().substring(0,10));
        holder.txt_name.setText(list.get(position).getComment());
        holder.txt_price.setText(String.valueOf(list.get(position).getPrice()));
        holder.linearLayout.setOnClickListener(v ->
        {
            onClickListener.onClick(v,position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txt_date;
        public TextView txt_name;
        public TextView txt_price;
        public LinearLayout linearLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            txt_date = (TextView)itemView.findViewById(R.id.buy_list_txt_date);
            txt_name = (TextView)itemView.findViewById(R.id.buy_list_txt_name);
            txt_price = (TextView)itemView.findViewById(R.id.buy_list_txt_price);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.buy_list_layout);
        }

    }
}
