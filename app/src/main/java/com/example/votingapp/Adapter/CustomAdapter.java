package com.example.votingapp.Adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.votingapp.Interface.ItemClickListener;

class CustomViewHolder extends RecyclerView.ViewHolder{

    ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public CustomViewHolder(View itemView) {
        super(itemView);
    }
}
public class CustomAdapter {
}
