package com.example.pizza_ordering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start  main activity
                Intent i = new Intent(Splash_Screen.this, Main_Activity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, 1500);
    }
    }
