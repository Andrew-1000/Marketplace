package com.example.marketplace.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketplace.R;
import com.example.marketplace.adapter.ProductAdapter;
import com.example.marketplace.model.Product;
import com.example.marketplace.network.MarketplaceClient;
import com.example.marketplace.network.MarketplaceInterface;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView productsRecyclerView;
    private ProductAdapter productAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MarketplaceInterface marketplaceInterface = MarketplaceClient.getMarketplaceInstance().create(MarketplaceInterface.class);
        Call<List<Product>> listCall = marketplaceInterface.getAllProducts();
        listCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.code() == 200) {
                    getProductsData(response.body());
                    productAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.home, menu );
        inflater.inflate( R.menu.logout, menu );
        return super.onCreateOptionsMenu( menu );
    }


    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int id =item.getItemId();
        if (id == R.id.home) {
            openBuyActivity();
        }
        if (id == R.id.logout) {
            logout();
        }
        return super.onOptionsItemSelected( item );
    }


    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent( BuyActivity.this, LoginActivity.class );
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
        startActivity( intent );
        finish();

    }

    private void openBuyActivity() {
        Intent intent = new Intent( BuyActivity.this, BuyActivity.class );
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK  );
        startActivity( intent );
    }

    private void getProductsData(List<Product> body) {
        productsRecyclerView = findViewById(R.id.product_list);
        productAdapter = new ProductAdapter(body);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        productsRecyclerView.setLayoutManager(layoutManager);
        productsRecyclerView.setAdapter(productAdapter);

    }

}
