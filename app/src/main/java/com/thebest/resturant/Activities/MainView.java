package com.thebest.resturant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thebest.resturant.R;
import androidx.appcompat.app.AppCompatActivity;

public class MainView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        findViewById(R.id.drawer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainView.this.startActivity(new Intent(MainView.this, DrawerView.class));
            }
        });
    }
}