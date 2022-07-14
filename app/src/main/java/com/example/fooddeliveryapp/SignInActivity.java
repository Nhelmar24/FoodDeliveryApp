package com.example.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fooddeliveryapp.databinding.ActivitySignInBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignInActivity extends AppCompatActivity{
    // View Binding
//    private ActivitySignInBinding binding;

    // Google Sign In
    Button googleSignInBtn;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

//    // ImageView btnFacebook;
//    private EditText editTextEmail, editTextPassword;
//    private TextView textViewForgotPassword, register;
//    private Button buttonSignIn;
//    private ProgressBar progressBar;
//    private FirebaseAuth mAuth;
//
//    boolean passwordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();

        // Sign In Using Google
        googleSignInBtn = findViewById(R.id.googleSignInBtn);
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc= GoogleSignIn.getClient(this,gso);
        googleSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn();
            }
        });

//        // Sign In Using Email
//        register = (TextView) findViewById(R.id.register);
//        register.setOnClickListener(this);
//
//        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
//        buttonSignIn.setOnClickListener(this);
//
//        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
//        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
//
//        progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        mAuth = FirebaseAuth.getInstance();

//        // converting password to dot characters
//        editTextPassword.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                final int Right=2;
//                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
//                    if(motionEvent.getRawX()>=editTextPassword.getRight()-editTextPassword.getCompoundDrawables()[Right].getBounds().width()){
//                        int selection=editTextPassword.getSelectionEnd();
//                        if(passwordVisible){
//                            editTextPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_visibility_off_24,0);
//                            editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                            passwordVisible=false;
//                        }else{
//                            editTextPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_visibility_24,0);
//                            editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                            passwordVisible=true;
//                        }
//                        editTextPassword.setSelection(selection);
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });
//    }

//    // SignIn with email
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.register:
//                startActivity(new Intent(this, RegisterActivity.class));
//                break;
//
//            case R.id.buttonSignIn:
//                userLogin();
//                break;
//        }
//    }






//    // Email Signin
//    private void userLogin() {
//        String email = editTextEmail.getText().toString().trim();
//        String password = editTextPassword.getText().toString().trim();
//
//        if(email.isEmpty()){
//            editTextEmail.setError("Email is required");
//            editTextEmail.requestFocus();
//            return;
//        }
//
//        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            editTextEmail.setError("Please enter valid email");
//            editTextEmail.requestFocus();
//            return;
//        }
//
//        if(password.isEmpty()){
//            editTextPassword.setError("Password is required");
//            editTextPassword.requestFocus();
//            return;
//        }
//
//        if(password.length() < 6){
//            editTextPassword.setError("Password must be min 6 characters");
//            editTextPassword.requestFocus();
//            return;
//        }
//
//        progressBar.setVisibility(View.VISIBLE);

//        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    //redirect to profile
//                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
//                }else{
//                    Toast.makeText(SignInActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }

    private void SignIn() {
        Intent intent = gsc.getSignInIntent();
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                ProfileActivity();
            }catch (ApiException e){
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void ProfileActivity() {
        finish();
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}