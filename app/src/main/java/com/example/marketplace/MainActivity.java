package com.example.marketplace;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.edtusername) EditText mUserName;
    @BindView(R.id.btnProceed) Button mProceed;
    @BindView(R.id.yourname) TextView getGetYourName;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mProceed.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == mProceed) {
            openActivity();
        }

    }

    private void openActivity() {
        String mUsername = mUserName.getText().toString();
        Log.d(TAG, mUsername);
        Intent intent = new Intent(MainActivity.this, StartActivity.class);
        intent.putExtra("mUsername", mUsername);
        startActivity(intent);
        Toast toast = Toast.makeText(MainActivity.this, "Welcome " + mUsername, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
