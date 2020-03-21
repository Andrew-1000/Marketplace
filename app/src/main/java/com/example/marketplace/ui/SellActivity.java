package com.example.marketplace.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.marketplace.R;
import com.example.marketplace.ui.SellFragments.DashboardFragment;
import com.example.marketplace.ui.SellFragments.HomeFragment;
import com.example.marketplace.ui.SellFragments.MyStoresFragment;
import com.example.marketplace.ui.SellFragments.OrdersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SellActivity extends AppCompatActivity implements  View.OnClickListener {
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        BottomNavigationView bottomNavigationView = findViewById( R.id.bottom_nav_view );

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        bottomNavigationView.setOnNavigationItemSelectedListener( item -> {
            Fragment fragment;
           switch (item.getItemId()) {
               case R.id.navigation_home:
                   fragment = new HomeFragment();
                   loadFragment(fragment);
                   Objects.requireNonNull( getSupportActionBar() ).setTitle( R.string.home );
                   break;

               case R.id.navigation_dashboard:
                   fragment = new DashboardFragment();
                   loadFragment( fragment );
                   Objects.requireNonNull( getSupportActionBar() ).setTitle(R.string.dashboard );
                    break;
               case R.id.navigation_orders:
                   fragment = new OrdersFragment();
                   loadFragment( fragment );
                   Objects.requireNonNull( getSupportActionBar() ).setTitle( R.string.my_orders );
                    break;
               case R.id.nav_my_local_stores:
                   fragment = new MyStoresFragment();
                   loadFragment( fragment );
                   Objects.requireNonNull( getSupportActionBar() ).setTitle( R.string.my_stores );
                   break;
           }
            return true;
        } );
    }
    private void loadFragment(Fragment fragment) {

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace( R.id.fragment_view, fragment );
            fragmentTransaction.commit();
        } else {
            Log.e( "SellActivity", "Error while creating fragment" );
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.home, menu );
        inflater.inflate(R.menu.logout, menu );
        return super.onCreateOptionsMenu( menu );
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            Intent intent = new Intent( SellActivity.this, BuyActivity.class);
            startActivity( intent );
        }
        if (id == R.id.logout){
            logout();
        }
        return super.onOptionsItemSelected( item );
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent( SellActivity.this, LoginActivity.class );
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
        startActivity( intent );
        finish();
    }
    @Override
    public void onClick(View v) {
        if (v == fab) {
            addCategory();
        }
    }
    private void addCategory() {
        Intent intent = new Intent(SellActivity.this, NewCategoryActivity.class);
        startActivity(intent);
    }
}
