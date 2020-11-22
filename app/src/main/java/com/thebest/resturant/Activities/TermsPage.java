package com.thebest.resturant.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.thebest.resturant.R;


public class TermsPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_view);
        findViewById(R.id.back).setOnClickListener(view -> finish());
    }
}
