package com.thebest.resturant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import com.thebest.resturant.R;
import com.thebest.resturant.Utilities.Constant;
import com.thebest.resturant.Utilities.SharedPrefDB;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_view);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!(new SharedPrefDB(SplashActivity.this.getApplicationContext()).GetBoolFromSharedPref(Constant.SharedPrefIsLogged)))
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this.getApplicationContext(), LoginActivity.class));
                else
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this.getApplicationContext(), AllCategories.class));
                SplashActivity.this.finish();
            }
        }, 2500);
    }
}