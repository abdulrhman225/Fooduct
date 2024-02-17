package com.example.fooduct.Orders;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooduct.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Custom_See_All_Adapter extends RecyclerView.Adapter<Custom_See_All_Adapter.all_Pro_ViewHolder> {
    ArrayList<All_Pro_same_section> all_Pro ;
    OnClickListener onClickListener;

    public Custom_See_All_Adapter (ArrayList<All_Pro_same_section> all_Pro , OnClickListener onClickListener){
        this.onClickListener = onClickListener;
        this.all_Pro = all_Pro;
    }

    @NonNull
    @Override
    public all_Pro_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product , parent , false);
        all_Pro_ViewHolder viewHolder = new all_Pro_ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull all_Pro_ViewHolder holder, int position) {
        All_Pro_same_section all_pro_same_section = all_Pro.get(position);
        Picasso.get().load(all_pro_same_section.Image).into(holder.iv);
        holder.tv_pro_Name.setText(all_pro_same_section.ProName);
        holder.tv_pro_price.setText(all_pro_same_section.ProPrice);
        holder.iv.setTag(position);


    }

    @Override
    public int getItemCount() {
        return all_Pro.size();
    }

    class all_Pro_ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv_pro_Name , tv_pro_price;
        public all_Pro_ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.Product_Image);
            tv_pro_Name = itemView.findViewById(R.id.Product_Name);
            tv_pro_price = itemView.findViewById(R.id.Product_Price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (int) iv.getTag();
                    onClickListener.onItemClick(position);
                }
            });
        }
    }
}
