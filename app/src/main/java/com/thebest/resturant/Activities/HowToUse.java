package com.thebest.resturant.Activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thebest.resturant.Adapters.AllHowToUseAdapter;
import com.thebest.resturant.R;

public class HowToUse extends AppCompatActivity {

    String[] ArrQuestions = {"How to Use Taxi ?","How to make order of taxi ?","How to Use Taxi ?","How to make order of taxi ?"};
    String[] ArrAnswers =
            {"Click icon and go to make a trip.","Click icon and go to make a trip and Click icon and go to make a trip.","Click icon and go to make a trip and Click icon and go to make a trip.","Click icon and go to make a trip and Click icon and go to make a trip."};

    int lastSelectedPos = 0;

    RecyclerView TextList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_to_us_screen);
        findViewById(R.id.drawer).setOnClickListener(v -> startActivity(new Intent(this, DrawerView.class)));

        TextList = findViewById(R.id.list);
        TextList.setNestedScrollingEnabled(true);
        TextList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,  LinearLayoutManager.VERTICAL,false);
        TextList.setLayoutManager(layoutManager);

        AllHowToUseAdapter adapter = new AllHowToUseAdapter(this,ArrQuestions,ArrAnswers);
        TextList.setAdapter(adapter);
    }

}
