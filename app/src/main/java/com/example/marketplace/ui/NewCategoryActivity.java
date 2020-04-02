package com.example.marketplace.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketplace.R;
import com.example.marketplace.adapter.CategoryViewHolder;
import com.example.marketplace.model.Category;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewCategoryActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.categoryName)
    TextInputEditText categoryName;
    @BindView(R.id.addCategoryButton)
    MaterialButton btnAddCategory;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase firebaseDatabase ;
    private DatabaseReference newCategoryReference;

    private RecyclerView categoriesRecyclerView;
    protected DatabaseReference mDatabase;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.home, menu );
        inflater.inflate( R.menu.logout, menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.home) {
            Intent intent = new Intent( NewCategoryActivity.this, BuyActivity.class );
            startActivity( intent );

        }
        if (itemId ==R.id.logout) {
            logout();
        }
        return super.onOptionsItemSelected( item );
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();

        SharedPreferences preferences = getSharedPreferences( "checkbox", MODE_PRIVATE );
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString( "remember", "false" );
        editor.apply();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);

        ButterKnife.bind(this);
        categoriesRecyclerView = findViewById( R.id.categoryRecyclerView );
        btnAddCategory.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        newCategoryReference = FirebaseDatabase.getInstance().getReference("Category");

        mDatabase = FirebaseDatabase.getInstance().getReference("Category");
        mDatabase.keepSynced( true );
        categoriesRecyclerView.setHasFixedSize( true );
        categoriesRecyclerView.setLayoutManager( new GridLayoutManager( this, 2 ) );
    }

    @Override
    public void onClick(View v) {
        if (v == btnAddCategory) {
            saveCategory();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Category, CategoryViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter <Category, CategoryViewHolder> (Category.class, R.layout.category_card, CategoryViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(CategoryViewHolder categoryViewHolder, Category category, int i) {

                categoryViewHolder.setCategory( category.getCategory_name() );
                categoryViewHolder.itemView.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent moreDetails = new Intent( NewCategoryActivity.this, SellActivity.class);
                        startActivity( moreDetails );
                    }
                } );
            }
        };
       categoriesRecyclerView.setAdapter( firebaseRecyclerAdapter );
    }
    private void saveCategory() {

        String category_name = Objects.requireNonNull( categoryName.getText() ).toString().trim();
        if (!TextUtils.isEmpty( category_name )) {
            String id = newCategoryReference.push().getKey();
            Category category = new Category(category_name);
            assert id != null;
            newCategoryReference.child( id ).setValue( category );
            Toast.makeText( NewCategoryActivity.this, "New Category Added Successfully", Toast.LENGTH_LONG ).show();
            categoryName.setText( "" );
        } else {
            Toast.makeText( NewCategoryActivity.this, "Please provide Category", Toast.LENGTH_LONG ).show();
        }

    }
}
