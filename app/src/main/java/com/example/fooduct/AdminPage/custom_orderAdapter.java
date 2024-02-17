package com.example.fooduct.AdminPage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooduct.R;

import java.util.ArrayList;

public class custom_orderAdapter extends RecyclerView.Adapter<custom_orderAdapter.orderViewHolder> {

    ArrayList<custom_order> orders;
    OnOrderClickListener orderClickListener;

    public custom_orderAdapter(ArrayList<custom_order> orders , OnOrderClickListener orderClickListener) {
        this.orders = orders;
        this.orderClickListener = orderClickListener;
    }

    @NonNull
    @Override
    public orderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_orders , parent , false);
        orderViewHolder holder = new orderViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull orderViewHolder holder, int position) {
        custom_order order = orders.get(position);
        holder.tv_userName.setText(order.getUserName());
        holder.tv_phoneNumber.setText(order.getPhoneNumber());

        holder.tv_userName.setTag(position);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class orderViewHolder extends RecyclerView.ViewHolder {
        TextView tv_userName;
        TextView  tv_phoneNumber;

        public orderViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_userName = itemView.findViewById(R.id.custom_orders_tv_userName);
            tv_phoneNumber = itemView.findViewById(R.id.custom_orders_tv_phoneNumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (int) tv_userName.getTag();
                    orderClickListener.onItemClick(position);
                }
            });
        }
    }
}
