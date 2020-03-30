package com.example.marketplace.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketplace.R;
import com.example.marketplace.model.FirebaseProducts;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FirebaseProductsImagesAdapter extends RecyclerView.Adapter<FirebaseProductsImagesAdapter.ProductViewHolder> {
    private Context mContext;
    private List<FirebaseProducts> mFirebaseProducts;


    public FirebaseProductsImagesAdapter(Context context, List<FirebaseProducts> firebaseProducts){
        mContext = context;
        mFirebaseProducts = firebaseProducts;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from( mContext ).inflate( R.layout.card_buy_customers, parent, false );
        return new ProductViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Picasso picasso = Picasso.get();
        picasso.setLoggingEnabled( true );
        FirebaseProducts firebaseProducts = mFirebaseProducts.get( position );
        holder.productName.setText( firebaseProducts.getName() );
        holder.productPrice.setText( firebaseProducts.getPrice() );
        picasso.get()
                .load( firebaseProducts.getImage() )

                .placeholder( R.drawable.placeholder )
                .into( holder.productImage );
    }

    @Override
    public int getItemCount() {
        return mFirebaseProducts.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productPrice;
        ImageView productImage;

        public ProductViewHolder(@NonNull View itemView) {
            super( itemView );
            productName = itemView.findViewById( R.id.name );
            productPrice = itemView.findViewById( R.id.price );
            productImage = itemView.findViewById( R.id.image );

        }
    }
}
