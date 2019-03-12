package com.example.votingapp.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.votingapp.Interface.ItemClickListener;
import com.example.votingapp.Model.Item;
import com.example.votingapp.R;

import java.util.List;

class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
public Button button1;
public Button button2;
    ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public CustomViewHolder(View itemView)
    {

        super(itemView);
        button1= (Button) itemView.findViewById(R.id.candidate1);
        button2= (Button) itemView.findViewById(R.id.candidate2);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
itemClickListener.onClick(v,getAdapterPosition());
    }
}
public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    List<Item> items;
    Context context;

    int row_index=-1;

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.president,parent,false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.button1.setText(items.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
