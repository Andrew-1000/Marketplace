package com.example.marketplace.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.marketplace.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.welcome) TextView mWelcome;
    @BindView(R.id.sell) Button mSell;
    @BindView(R.id.buy) Button mBuy;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        toolbar = findViewById( R.id.app_bar );
        setSupportActionBar( toolbar );

        ButterKnife.bind(this);

        mSell.setOnClickListener(this);
        mBuy.setOnClickListener(this);

        Intent intent = getIntent();
        String musername = intent.getStringExtra("mUsername");
        mWelcome.setText(String.format("%s%s%s", getString(R.string.hi), getString(R.string.space), musername));
    }

    @Override
    public void onClick(View v) {
        if (v == mSell) {
            openSellActivity();
        } else if ( v == mBuy) {
            openBuyActivity();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.home,menu );
        inflater.inflate( R.menu.logout,menu );
        return super.onCreateOptionsMenu( menu );
    }

    private void openBuyActivity() {
        Intent browserIntent = new Intent(StartActivity.this, BuyActivity.class);
        startActivity(browserIntent);
    }


    private void openSellActivity() {
        Intent browserIntent = new Intent(StartActivity.this, SellActivity.class);
        startActivity(browserIntent);
    }


}