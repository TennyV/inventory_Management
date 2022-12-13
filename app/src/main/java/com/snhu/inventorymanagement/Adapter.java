package com.snhu.inventorymanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private Context context;
    private ArrayList itemName, itemQuantity;

    public Adapter(Context context, ArrayList itemName, ArrayList itemQuantity) {
        this.context = context;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
        holder.itemName.setText(String.valueOf(itemName.get(position)));
        holder.itemQuantity.setText(String.valueOf(itemQuantity.get(position)));

    }

    @Override
    public int getItemCount() {

        return itemName.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemQuantity;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemname);
            itemQuantity = itemView.findViewById(R.id.itemQuantity);
        }
    }
}
