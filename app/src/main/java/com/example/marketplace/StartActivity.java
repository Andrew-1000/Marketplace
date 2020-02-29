package com.example.marketplace;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.welcome) TextView mWelcome;
    @BindView(R.id.sell) Button mSell;
    @BindView(R.id.buy) Button mBuy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ButterKnife.bind(this);

        mSell.setOnClickListener(this);
        mBuy.setOnClickListener(this);

        Intent intent = getIntent();
        String musername = intent.getStringExtra("mUsername");
        mWelcome.setText(String.format("%s%s%s", getString(R.string.welcome), getString(R.string.space), musername));
    }

    @Override
    public void onClick(View v) {
        if (v == mSell) {
            openSellActivity();
        } else if ( v == mBuy) {
            openBuyActivity();
        }
    }

    private void openBuyActivity() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.jumia.co.ke/"));
        startActivity(browserIntent);
    }

    private void openSellActivity() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.jumia.co.ke/market-place/"));
        startActivity(browserIntent);
    }


}
