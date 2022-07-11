package com.example.fooddeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        TextView textView = findViewById(R.id.textSplashScreen);
//        TextView.animate().translationX(1000).setDuration(1000).setStartDelay(2500);

        Thread thread = new Thread(){
            public void run(){
                try{
                    Thread.sleep(Long.parseLong("4000"));
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(SplashActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        thread.start();


    }
}