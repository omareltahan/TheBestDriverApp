package com.thebest.resturant.Activities;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thebest.resturant.Models.Requests.RequestRegister;
import com.thebest.resturant.Models.Responses.LoginResponse;
import com.thebest.resturant.Models.Responses.MainLoginResponse;
import com.thebest.resturant.Models.WorkingHoursModel;
import com.thebest.resturant.Networking.ServiceGenerator;
import com.thebest.resturant.Networking.StoreAPIInterface;
import com.thebest.resturant.R;
import com.thebest.resturant.Utilities.Constant;
import com.thebest.resturant.Utilities.SharedPrefDB;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileView extends AppCompatActivity {
    TextView sat_first_from,sat_first_to,sat_second_from,sat_second_to;
    TextView sun_first_from,sun_first_to,sun_second_from,sun_second_to;
    TextView mon_first_from,mon_first_to,mon_second_from,mon_second_to;
    TextView tues_first_from,tues_first_to,tues_second_from,tues_second_to;
    TextView wed_first_from,wed_first_to,wed_second_from,wed_second_to;
    TextView thurs_first_from,thurs_first_to,thurs_second_from,thurs_second_to;
    TextView fri_first_from,fri_first_to,fri_second_from,fri_second_to;
    ImageView back,logo;
    TextView desc , desc_ar ,name_ar, name,minorder,address,address_ar,fees;
    TextView selectedTimeTxT;
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


        sat_first_from.setOnClickListener(view -> {
            selectedTimeTxT = sat_first_from;
            GotoCalendar();
        });
        sat_first_to.setOnClickListener(view -> {
            selectedTimeTxT = sat_first_to;
            GotoCalendar();
        });
        sat_second_from.setOnClickListener(view -> {
            selectedTimeTxT = sat_second_from;
            GotoCalendar();
        });
        sat_second_to.setOnClickListener(view -> {
            selectedTimeTxT = sat_second_to;
            GotoCalendar();
        });

        sun_first_from.setOnClickListener(view -> {
            selectedTimeTxT = sun_first_from;
            GotoCalendar();
        });
        sun_first_to.setOnClickListener(view -> {
            selectedTimeTxT = sun_first_to;
            GotoCalendar();
        });
        sun_second_from.setOnClickListener(view -> {
            selectedTimeTxT = sun_second_from;
            GotoCalendar();
        });
        sun_second_to.setOnClickListener(view -> {
            selectedTimeTxT = sun_second_to;
            GotoCalendar();
        });

        mon_first_from.setOnClickListener(view -> {
            selectedTimeTxT = mon_first_from;
            GotoCalendar();
        });
        mon_first_to.setOnClickListener(view -> {
            selectedTimeTxT = mon_first_to;
            GotoCalendar();
        });
        mon_second_from.setOnClickListener(view -> {
            selectedTimeTxT = mon_second_from;
            GotoCalendar();
        });
        mon_second_to.setOnClickListener(view -> {
            selectedTimeTxT = mon_second_to;
            GotoCalendar();
        });

        tues_first_from.setOnClickListener(view -> {
            selectedTimeTxT = tues_first_from;
            GotoCalendar();
        });
        tues_first_to.setOnClickListener(view -> {
            selectedTimeTxT = tues_first_to;
            GotoCalendar();
        });
        tues_second_from.setOnClickListener(view -> {
            selectedTimeTxT = tues_second_from;
            GotoCalendar();
        });
        tues_second_to.setOnClickListener(view -> {
            selectedTimeTxT = tues_second_to;
            GotoCalendar();
        });

        wed_first_from.setOnClickListener(view -> {
            selectedTimeTxT = wed_first_from;
            GotoCalendar();
        });
        wed_first_to.setOnClickListener(view -> {
            selectedTimeTxT = wed_first_to;
            GotoCalendar();
        });
        wed_second_from.setOnClickListener(view -> {
            selectedTimeTxT = wed_second_from;
            GotoCalendar();
        });
        wed_second_to.setOnClickListener(view -> {
            selectedTimeTxT = wed_second_to;
            GotoCalendar();
        });

        thurs_first_from.setOnClickListener(view -> {
            selectedTimeTxT = thurs_first_from;
            GotoCalendar();
        });
        thurs_first_to.setOnClickListener(view -> {
            selectedTimeTxT = thurs_first_to;
            GotoCalendar();
        });
        thurs_second_from.setOnClickListener(view -> {
            selectedTimeTxT = thurs_second_from;
            GotoCalendar();
        });
        thurs_second_to.setOnClickListener(view -> {
            selectedTimeTxT = thurs_second_to;
            GotoCalendar();
        });

        fri_first_from.setOnClickListener(view -> {
            selectedTimeTxT = fri_first_from;
            GotoCalendar();
        });
        fri_first_to.setOnClickListener(view -> {
            selectedTimeTxT = fri_first_to;
            GotoCalendar();
        });
        fri_second_from.setOnClickListener(view -> {
            selectedTimeTxT = fri_second_from;
            GotoCalendar();
        });
        fri_second_to.setOnClickListener(view -> {
            selectedTimeTxT = fri_second_to;
            GotoCalendar();
        });
    }
    void GotoCalendar(){
        final Calendar calendar = Calendar.getInstance();
        (new TimePickerDialog(this, R.style.DialogTheme , time, calendar
                .get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),false)).show();
    }
    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            selectedTimeTxT.setText(hourOfDay + ":" + minute);
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view);
        setupDateView();
        desc_ar = findViewById(R.id.desc_ar);
        desc = findViewById(R.id.desc);
        name_ar = findViewById(R.id.namear);
        name = findViewById(R.id.name);
        minorder = findViewById(R.id.minorder);
        address_ar = findViewById(R.id.addressar);
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
        desc.setText(sharedPrefDB.getDescription_en()+"\n"+sharedPrefDB.getDescription());
        name.setText(sharedPrefDB.getName_en()+"\n"+sharedPrefDB.getName_en());

        desc.setText(sharedPrefDB.getDescription_en()+"\n"+sharedPrefDB.getDescription_en());
        address.setText(sharedPrefDB.getAddress_en()+"\n"+sharedPrefDB.getAddress());
        name_ar.setText(sharedPrefDB.getName_en()+"\n"+sharedPrefDB.getName());
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
    Dialog dialog;
    public void ShowDialogView(){
        if (dialog != null) {
            dialog.show();
            return;
        }
        dialog = new Dialog(this,R.style.Theme_Dialog);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_item);
        Glide.with(this).asGif().load(R.drawable.progress_image).into((ImageView) dialog.findViewById(R.id.image));
        final Window dialogWindow = dialog.getWindow();
        final WindowManager.LayoutParams dialogWindowAttributes = dialogWindow.getAttributes();
        final WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogWindowAttributes);
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity =  Gravity.CENTER;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }
    public void DismissDialogView() {
        dialog.dismiss();
    }

    void MakeRequest(String token){

        ShowDialogView();
        RequestRegister request = new RequestRegister();
        request.setName_en(name.getText().toString());
        request.setName_ar(name_ar.getText().toString());

        request.setAddress(address.getText().toString());
        request.setAddress_en(address_ar.getText().toString());

        request.setDescription(desc.getText().toString());
        request.setDescription_en(desc_ar.getText().toString());

        request.setOrder_limit(fees.getText().toString());
        request.setDelivery_price(minorder.getText().toString());

        WorkingHoursModel sat = new WorkingHoursModel();
        sat.setDay("saturday");
        sat.setStart(sat_first_from.getText().toString());
        sat.setEnd(sat_first_to.getText().toString());
        sat.setStart2(sat_second_from.getText().toString());
        sat.setEnd(sat_second_to.getText().toString());
        request.getWorking_hours().add(sat);
        sat.setDay("sunday");
        sat.setStart(sun_first_from.getText().toString());
        sat.setEnd(sun_first_to.getText().toString());
        sat.setStart2(sun_second_from.getText().toString());
        sat.setEnd(sun_second_to.getText().toString());
        request.getWorking_hours().add(sat);
        sat.setDay("monday");
        sat.setStart(mon_first_from.getText().toString());
        sat.setEnd(mon_first_to.getText().toString());
        sat.setStart2(mon_second_from.getText().toString());
        sat.setEnd(mon_second_to.getText().toString());
        request.getWorking_hours().add(sat);
        sat.setDay("tuesday");
        sat.setStart(tues_first_from.getText().toString());
        sat.setEnd(tues_first_to.getText().toString());
        sat.setStart2(tues_second_from.getText().toString());
        sat.setEnd(tues_second_to.getText().toString());
        request.getWorking_hours().add(sat);
        sat.setDay("wednesday");
        sat.setStart(wed_first_from.getText().toString());
        sat.setEnd(wed_first_to.getText().toString());
        sat.setStart2(wed_second_from.getText().toString());
        sat.setEnd(wed_second_to.getText().toString());
        request.getWorking_hours().add(sat);
        sat.setDay("thursday");
        sat.setStart(thurs_first_from.getText().toString());
        sat.setEnd(thurs_first_to.getText().toString());
        sat.setStart2(thurs_second_from.getText().toString());
        sat.setEnd(thurs_second_to.getText().toString());
        request.getWorking_hours().add(sat);
        sat.setDay("friday");
        sat.setStart(fri_first_from.getText().toString());
        sat.setEnd(fri_first_to.getText().toString());
        sat.setStart2(fri_second_from.getText().toString());
        sat.setEnd(fri_second_to.getText().toString());
        request.getWorking_hours().add(sat);


        request.setFcm_token(token);
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.MAIN_URL);
        storeServices.MakeRegister(request)
                .enqueue(new Callback<MainLoginResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MainLoginResponse> call,
                                           @NonNull Response<MainLoginResponse> response) {
                        DismissDialogView();
                        SharedPrefDB sharedPrefDB = new SharedPrefDB(EditProfileView.this);
                        sharedPrefDB.cacheShopData(EditProfileView.this, response.body());
                        startActivity(new Intent(EditProfileView.this,AllCategories.class));
                        finish();
                    }

                    @Override
                    public void onFailure(@NonNull Call<MainLoginResponse> call, @NonNull Throwable t) {
                        DismissDialogView();
                        Toast.makeText(EditProfileView.this,"failed  .. try again",Toast.LENGTH_LONG).show();
                    }
                });
    }

}