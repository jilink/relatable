package com.punchips.relatable;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    private static  int TIME = 1589; // temps de chargement (1,5 secondes)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent myIntent = new Intent(SplashScreen.this, Home.class);
                startActivity(myIntent);
                finish();

            }
        },TIME);


    }
}
