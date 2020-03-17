package com.example.marketplace.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.marketplace.R;
import com.example.marketplace.model.Product;
import com.squareup.picasso.Picasso;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    TextView price;
    TextView description;
    private ImageView imageView;
    Context context;


    public ProductViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        price = mView.findViewById(R.id.price);

        imageView = mView.findViewById(R.id.image);
        context = itemView.getContext();
    }
    public void bindProducts(Product product) {
        Picasso.get().load(product.getImage()).into(imageView);
        price.setText(product.getPrice());


    }
}
