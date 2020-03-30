package com.example.marketplace.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketplace.R;
import com.example.marketplace.adapter.FirebaseProductsImagesAdapter;
import com.example.marketplace.model.FirebaseProducts;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class CustomersBuyActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    FirebaseProductsImagesAdapter firebaseProductsImagesAdapter;
    private List<FirebaseProducts> productsUploads;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_buy_customers );

        recyclerView  = findViewById( R.id.product_list );

        recyclerView.setHasFixedSize( true );


        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        productsUploads = new ArrayList<>();

        databaseReference = firebaseDatabase.getReference("Products");
        storageReference = firebaseStorage.getReference("Products");

        databaseReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot productSnapshot : dataSnapshot.getChildren()){
                    FirebaseProducts firebaseProducts = productSnapshot.getValue(FirebaseProducts.class);
                    productsUploads.add(firebaseProducts);
                }
                firebaseProductsImagesAdapter = new FirebaseProductsImagesAdapter( CustomersBuyActivity.this, productsUploads );

                recyclerView.setAdapter( firebaseProductsImagesAdapter );
                firebaseProductsImagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( getApplicationContext(), R.string.permision_denied, Toast.LENGTH_LONG).show();

            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.cart, menu );
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
        Intent intent = new Intent( getApplicationContext(), LoginActivity.class );
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
        startActivity( intent );
        finish();

    }

    private void openBuyActivity() {
        Intent intent = new Intent( getApplicationContext(), BuyActivity.class );
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK  );
        startActivity( intent );
    }
    public void onCustomToggleClick(View view) {
        Toast.makeText( this, "Liked", Toast.LENGTH_SHORT ).show();
    }


}