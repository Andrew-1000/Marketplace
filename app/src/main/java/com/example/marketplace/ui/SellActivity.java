package com.example.marketplace.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.marketplace.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SellActivity extends AppCompatActivity implements  View.OnClickListener {
    FloatingActionButton fab;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.home, menu );
        inflater.inflate(R.menu.logout, menu );
        return super.onCreateOptionsMenu( menu );
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
