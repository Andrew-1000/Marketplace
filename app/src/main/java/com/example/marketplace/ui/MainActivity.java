package com.example.marketplace.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.marketplace.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.edtusername) EditText mUserName;
    @BindView(R.id.btnProceed) Button mProceed;
    @BindView(R.id.yourname) TextView getGetYourName;
    ProgressDialog progressDialog;
    Toolbar toolbar;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Welcome " + mUserName);
        ButterKnife.bind(this);
        mProceed.setOnClickListener(this);
        toolbar = findViewById( R.id.app_bar );
        setSupportActionBar( toolbar );



        Intent intent = getIntent();
        String musername = intent.getStringExtra("email");
        mUserName.setText( musername);
    }
    @Override
    public void onClick(View v) {
        if (v == mProceed) {
            openActivity();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.home, menu );
        inflater.inflate( R.menu.logout, menu );
        return super.onCreateOptionsMenu( menu );
    }

    private void openActivity() {
        String mUsername = mUserName.getText().toString();
        Log.d(TAG, mUsername);
        Intent intent = new Intent(MainActivity.this, StartActivity.class);
        intent.putExtra("mUsername", mUsername);
        startActivity(intent);
        progressDialog.dismiss();
        Toast toast = Toast.makeText(MainActivity.this, "Welcome " + mUsername, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
