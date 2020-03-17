package com.example.marketplace.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.marketplace.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity  implements View.OnClickListener {
    @BindView(R.id.email_address)
    TextInputEditText emailAddress;
    @BindView(R.id.userPassword)
    TextInputEditText userPassword;
    @BindView(R.id.openSignIn)
    TextView mOpenSignIn;
    @BindView(R.id.registerUser)
    MaterialButton registerButton;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        registerButton.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        if (v == registerButton) {
            createUserWithEmailAndPassword();
        }

    }

    private void createUserWithEmailAndPassword() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating new user");
        progressDialog.setMessage("You're being registered");
        progressDialog.show();

        String email = emailAddress.getText().toString().trim();
        String password = userPassword.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        openLogin();
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "New User Created Successfully", Toast.LENGTH_LONG).show();


                    } else {
                        Toast.makeText(RegisterActivity.this, "An Error occured, please try again..", Toast.LENGTH_LONG).show();
                    }
                } );
    }

    private void openLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}
