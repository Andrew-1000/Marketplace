package com.example.marketplace.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.marketplace.model.Product;
import com.example.marketplace.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> productList;
    private Product product;

    public ProductAdapter(List<Product> productList){
        this.productList = productList;
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView price;
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
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ProductViewHolder(view);
    }




    @Override
    public void onBindViewHolder(ProductAdapter.ProductViewHolder holder, int position) {
        holder.bindProducts(productList.get(position));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
