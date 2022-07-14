package com.example.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fooddeliveryapp.databinding.ActivityProfileBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    // Google SignIn
    TextView name, email;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    //view binding
    private ActivityProfileBinding binding;

    //google sign in
    private FirebaseAuth firebaseAuth;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        name=findViewById(R.id.fullName);
        email=findViewById(R.id.emailAddress);
        logout=findViewById(R.id.signOut);
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc= GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            String Name = account.getDisplayName();
            String Mail = account.getEmail();

            name.setText(Name);
            name.setText(Mail);
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignOut();
            }
        });


//        logout = (Button) findViewById(R.id.signOut);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
//            }
//        });

        //Google Signout
//        firebaseAuth = FirebaseAuth.getInstance();
//        checkUser();
//
//        binding.signOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                firebaseAuth.signOut();
//                checkUser();
//            }
//        });


//        START EMAIL AND PASSWORD SIGN IN

//        user = FirebaseAuth.getInstance().getCurrentUser();
//        reference = FirebaseDatabase.getInstance().getReference("Users");
//        userID = user.getUid();
//
//        final TextView fullNameTextView =  (TextView) findViewById(R.id.fullName);
//        final TextView emailTextView =  (TextView) findViewById(R.id.emailAddress);
//        final TextView birthDayTextView =  (TextView) findViewById(R.id.birthDay);
//
//        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                User userProfile = snapshot.getValue(User.class);
//
//                if(userProfile != null){
//                    String fullName = userProfile.fullName;
//                    String email = userProfile.email;
//                    String birthDay = userProfile.birthday;
//
//                    fullNameTextView.setText(fullName);
//                    emailTextView.setText(email);
//                    birthDayTextView.setText(birthDay);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(ProfileActivity.this,"Something wrong happened",Toast.LENGTH_LONG).show();
//            }
//        });
//
//
//    }
// END EMAIL AND PASSWORD SIGN IN ////

//    private void checkUser() {
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        if(firebaseUser != null){
//            startActivity(new Intent(this,SignInActivity.class));
//            finish();
//        }else {
//            String email = firebaseUser.getEmail();
//            binding.emailAddress.setText(email);
//        }
//    }
}

    private void SignOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(getApplicationContext(),SignInActivity.class));
            }
        });
    }
    }