package com.example.finalseizureyarab.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalseizureyarab.R;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

// Using handler with postDelayed called runnable run method

            @Override

            public void run() {

                Intent intent = new Intent(SplashActivity.this , Login.class);
                startActivity(intent);

                // close this activity

                finish();

            }

        },  2500); // wait for 5 seconds
    }
}