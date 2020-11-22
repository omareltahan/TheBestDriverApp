package com.thebest.resturant.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thebest.resturant.Adapters.FoodShopCategoryAdapter;
import com.thebest.resturant.Models.MainWorkingHoursModel;
import com.thebest.resturant.Models.Requests.AddMenuItem;
import com.thebest.resturant.Models.Responses.LoginResponse;
import com.thebest.resturant.Models.Responses.MainLoginResponse;
import com.thebest.resturant.Models.Responses.MyMenusItemModel;
import com.thebest.resturant.Models.Responses.MyMenusMainResponse;
import com.thebest.resturant.Models.WorkingHoursModel;
import com.thebest.resturant.Networking.ServiceGenerator;
import com.thebest.resturant.Networking.StoreAPIInterface;
import com.thebest.resturant.R;
import com.thebest.resturant.Utilities.Constant;
import com.thebest.resturant.Utilities.SharedPrefDB;
import com.thebest.resturant.listenToOpenEdit;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileView extends AppCompatActivity {
    TextView sat_first_from,sat_first_to,sat_second_from,sat_second_to;
    TextView sun_first_from,sun_first_to,sun_second_from,sun_second_to;
    TextView mon_first_from,mon_first_to,mon_second_from,mon_second_to;
    TextView tues_first_from,tues_first_to,tues_second_from,tues_second_to;
    TextView wed_first_from,wed_first_to,wed_second_from,wed_second_to;
    TextView thurs_first_from,thurs_first_to,thurs_second_from,thurs_second_to;
    TextView fri_first_from,fri_first_to,fri_second_from,fri_second_to;


    ImageView back,logo;
    TextView desc, name,minorder,address,fees;
    void setupDateView(){
        sat_first_from = findViewById(R.id.sat_first_from);
        sat_first_to = findViewById(R.id.sat_first_to);
        sat_second_from = findViewById(R.id.sat_second_from);
        sat_second_to = findViewById(R.id.sat_second_to);

        sun_first_from = findViewById(R.id.sun_first_from);
        sun_first_to = findViewById(R.id.sun_first_to);
        sun_second_from = findViewById(R.id.sun_second_from);
        sun_second_to = findViewById(R.id.sun_second_to);

        mon_first_from = findViewById(R.id.mon_first_from);
        mon_first_to = findViewById(R.id.mon_first_to);
        mon_second_from = findViewById(R.id.mon_second_from);
        mon_second_to = findViewById(R.id.mon_second_to);

        tues_first_from = findViewById(R.id.tues_first_from);
        tues_first_to = findViewById(R.id.tues_first_to);
        tues_second_from = findViewById(R.id.tues_second_from);
        tues_second_to = findViewById(R.id.tues_second_to);

        wed_first_from = findViewById(R.id.wed_first_from);
        wed_first_to = findViewById(R.id.wed_first_to);
        wed_second_from = findViewById(R.id.wed_second_from);
        wed_second_to = findViewById(R.id.wed_second_to);

        thurs_first_from = findViewById(R.id.thurs_first_from);
        thurs_first_to = findViewById(R.id.thurs_first_to);
        thurs_second_from = findViewById(R.id.thurs_second_from);
        thurs_second_to = findViewById(R.id.thurs_second_to);

        fri_first_from = findViewById(R.id.fri_first_from);
        fri_first_to = findViewById(R.id.fri_first_to);
        fri_second_from = findViewById(R.id.fri_second_from);
        fri_second_to = findViewById(R.id.fri_second_to);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view);
        setupDateView();
        desc = findViewById(R.id.desc);
        name = findViewById(R.id.name);
        minorder = findViewById(R.id.minorder);
        address = findViewById(R.id.address);
        fees = findViewById(R.id.fees);
        logo = findViewById(R.id.logo);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        LoginResponse sharedPrefDB = (new SharedPrefDB(this).getShopData(this).getMyresturant());
        Glide.with(this).load(sharedPrefDB.getImage()).into(logo);
        desc.setText(sharedPrefDB.getDescription_en()+"\n"+sharedPrefDB.getDescription_en());
        address.setText(sharedPrefDB.getAddress_en()+"\n"+sharedPrefDB.getAddress());
        name.setText(sharedPrefDB.getName_en()+"\n"+sharedPrefDB.getName());
        minorder.setText(sharedPrefDB.getOrder_limit()+getResources().getString(R.string.kwd));
        fees.setText(sharedPrefDB.getDelivery_price() + getResources().getString(R.string.kwd));

        ArrayList<WorkingHoursModel> model = new Gson().fromJson(sharedPrefDB.getWorking_hours().toString(), new TypeToken<List<WorkingHoursModel>>(){}.getType());
        sat_first_from.setText(model.get(0).getStart());
        sat_first_to.setText(model.get(0).getEnd());
        sat_second_from.setText(model.get(0).getStart2());
        sat_second_to.setText(model.get(0).getEnd2());

        sun_first_from.setText(model.get(1).getStart());
        sun_first_to.setText(model.get(1).getEnd());
        sun_second_from.setText(model.get(1).getStart2());
        sun_second_to.setText(model.get(1).getEnd2());

        mon_first_from.setText(model.get(2).getStart());
        mon_first_to.setText(model.get(2).getEnd());
        mon_second_from.setText(model.get(2).getStart2());
        mon_second_to.setText(model.get(2).getEnd2());

        tues_first_from.setText(model.get(3).getStart());
        tues_first_to.setText(model.get(3).getEnd());
        tues_second_from.setText(model.get(3).getStart2());
        tues_second_to.setText(model.get(3).getEnd2());

        wed_first_from.setText(model.get(4).getStart());
        wed_first_to.setText(model.get(4).getEnd());
        wed_second_from.setText(model.get(4).getStart2());
        wed_second_to.setText(model.get(4).getEnd2());

//        thurs_first_from.setText(model.get(5).getStart());
//        thurs_first_to.setText(model.get(5).getEnd());
//        thurs_second_from.setText(model.get(5).getStart2());
//        thurs_second_to.setText(model.get(5).getEnd2());
//
//        fri_first_from.setText(model.get(6).getStart());
//        fri_first_to.setText(model.get(6).getEnd());
//        fri_second_from.setText(model.get(6).getStart2());
//        fri_second_to.setText(model.get(6).getEnd2());


    }
}