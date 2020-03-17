package com.example.marketplace.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketplace.R;

public  class CategoryViewHolder extends RecyclerView.ViewHolder {
    View mView;
    public CategoryViewHolder(@NonNull View itemView) {
        super( itemView );
        mView = itemView;
    }
    public void setCategory(String category){
        TextView catName =  mView.findViewById( R.id.categoryName );
        catName.setText( category );
    }

}
