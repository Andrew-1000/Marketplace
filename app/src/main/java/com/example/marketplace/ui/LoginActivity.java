package com.example.marketplace.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.marketplace.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.email_address)
    TextInputEditText mEmailAddress;
    @BindView(R.id.password)
    TextInputEditText mPassword;
    @BindView(R.id.signed_in)
    CheckBox mCheckBox;
    @BindView(R.id.add_to_cart)
    MaterialButton mSignin;


    //Firebase
    private FirebaseAuth mAuth;
    private ProgressDialog loginProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //Instantiate
        mAuth = FirebaseAuth.getInstance();

        loginProgressDialog = new ProgressDialog(this);
        SharedPreferences preferences = getSharedPreferences( "checkbox", MODE_PRIVATE );
        String checkbox = preferences.getString(  "remember", "");
        if (checkbox.equals( "true" )){
            Intent intent = new Intent( LoginActivity.this, CustomersBuyActivity.class );
            startActivity( intent );
        } else  if (checkbox.equals( "false" )) {
            Toast.makeText( this, "Please sign in..", Toast.LENGTH_SHORT ).show();
        }
        mSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        mCheckBox.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (buttonView.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences( "checkbox", MODE_PRIVATE );
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString( "remember", "true" );
                    editor.apply();
                    Toast.makeText( LoginActivity.this, "Checked", Toast.LENGTH_SHORT ).show();
                } else  if (!buttonView.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences( "checkbox", MODE_PRIVATE );
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString( "remember", "false" );
                    editor.apply();
                    Toast.makeText( LoginActivity.this, "Uchecked", Toast.LENGTH_SHORT ).show();
                }

            }
        } );
    }
    private void signIn() {
        mEmailAddress.setError(null);
        mPassword.setError(null);
        final String email = mEmailAddress.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

          if (TextUtils.isEmpty(email)) {
            mEmailAddress.setError("Email is Required");
            focusView = mEmailAddress;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailAddress.setError("Please provide a valid email address");
            focusView = mEmailAddress;
            cancel = true;
        } if (TextUtils.isEmpty(password)) {
            mPassword.setError("Password is required");
            focusView = mPassword;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPassword.setError("Password length should be more than 4 characters");
            focusView = mPassword;
            cancel = true;
        }
         if (cancel) {
             focusView.requestFocus();
         } else {
             loginProgressDialog.show();
             loginProgressDialog.setMessage("Logging in..");

             mAuth.signInWithEmailAndPassword(email, password)
                     .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if (task.isSuccessful()) {
                                 Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                 intent.putExtra("email", email);
                                 intent.putExtra("password", password);
                                 intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                                 startActivity(intent);
                                 loginProgressDialog.dismiss();
                                 finish();
                             }  else {
                                 loginProgressDialog.dismiss();
                                 Toast.makeText(LoginActivity.this, "Username or password incorrect", Toast.LENGTH_LONG).show();
                             }
                         }

                     });
             }
        }

    private boolean isEmailValid(String email) {
        return  email.contains("@");
    }
    private boolean isPasswordValid(String password) {
        return password.length()>=4;
    }

    public void openRegisterActivity(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
