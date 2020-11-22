package com.thebest.resturant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.thebest.resturant.R;
import com.thebest.resturant.Utilities.SharedPrefDB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ReportFragment;

public class DrawerView extends AppCompatActivity {

    ImageView profileimage,back;
    View viewEmpty;
    TextView main,reports,share,howuse,order,rate,profile,settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_view);


        ((Switch)findViewById(R.id.aSwitch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    ((Switch)findViewById(R.id.aSwitch)).setText("متاح");
                else
                    ((Switch)findViewById(R.id.aSwitch)).setText("غير متاح");
            }
        });

        settings = findViewById(R.id.settings);
        profileimage = findViewById(R.id.profileimage);
        back = findViewById(R.id.back);
        order = findViewById(R.id.lastorders);
        viewEmpty = findViewById(R.id.viewEmpty);
        main = findViewById(R.id.main);
        reports = findViewById(R.id.reports);
        profile = findViewById(R.id.profile);
        share = findViewById(R.id.share);
        howuse = findViewById(R.id.howuse);
        rate = findViewById(R.id.rate);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerView.this.finish();
            }
        });
        viewEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerView.this.finish();
            }
        });
        findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//            startActivity(new Intent(DrawerView.this, UpdateView.class));
            }
        });
        SharedPrefDB sharedPrefDB = new SharedPrefDB(this);
        Glide.with(this).load(sharedPrefDB.getShopData(this).getMyresturant().getImage()).into(profileimage);
        if (getIntent().getExtras()==null){
            main.setAlpha(1f);
        }
        else {
            findViewById(getIntent().getExtras().getInt("id")).setAlpha(1f);
        }


        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerView.this.startActivity(new Intent(DrawerView.this, reportsview.class));
            }
        });
        share.setOnClickListener(view -> {
            startActivity(new Intent(DrawerView.this, ShareUs.class));
        });
        howuse.setOnClickListener(view -> {
            startActivity(new Intent(DrawerView.this, HowToUse.class));
        });
        settings.setOnClickListener(view -> {
            startActivity(new Intent(DrawerView.this, TermsPage.class));

        });
        rate.setOnClickListener(view -> {
            Toast.makeText(DrawerView.this,"it will work when we go live on play store",Toast.LENGTH_LONG).show();
        });

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DrawerView.this,AllCategories.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DrawerView.this,ProfileView.class));
            }
        });
        findViewById(R.id.language).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DrawerView.this,EditSettingLanguage.class));
            }
        });
        order.setOnClickListener(view -> startActivity(new Intent(DrawerView.this,AllNewOrders.class)));
        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DrawerView.this,ProfileView.class));
            }
        });

    }
}