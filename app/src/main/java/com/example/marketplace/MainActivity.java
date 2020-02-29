package com.example.selfawareness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.edtusername) EditText username;
    @BindView(R.id.btnProceed) Button proceed;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        proceed.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == proceed) {
            openActivity();
        }

    }

    private void openActivity() {
        Intent intent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(intent);
    }
}
