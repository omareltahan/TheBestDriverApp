package com.thebest.resturant.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.thebest.resturant.Adapters.CheckListAdapter;
import com.thebest.resturant.Adapters.FoodShopCategoryAdapter;
import com.thebest.resturant.Models.Requests.GetCitiesItemModel;
import com.thebest.resturant.Models.Requests.GetCitiesMainModel;
import com.thebest.resturant.Models.Requests.GetDistrictsMainModel;
import com.thebest.resturant.Models.Requests.RequestRegister;
import com.thebest.resturant.Models.Responses.FoodMainItem;
import com.thebest.resturant.Models.Responses.FoodMainItemResponse;
import com.thebest.resturant.Models.Responses.MainLoginResponse;
import com.thebest.resturant.Models.Responses.MyMenusMainResponse;
import com.thebest.resturant.Models.WorkingHoursModel;
import com.thebest.resturant.Models.responsiblesModel;
import com.thebest.resturant.Networking.ServiceGenerator;
import com.thebest.resturant.Networking.StoreAPIInterface;
import com.thebest.resturant.R;
import com.thebest.resturant.Utilities.Constant;
import com.thebest.resturant.Utilities.MyFirebaseInstanceIDService;
import com.thebest.resturant.Utilities.SharedPrefDB;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    ArrayList<GetCitiesItemModel>  arrListCities;

    TextView sat_first_from,sat_first_to,sat_second_from,sat_second_to;
    TextView sun_first_from,sun_first_to,sun_second_from,sun_second_to;
    TextView mon_first_from,mon_first_to,mon_second_from,mon_second_to;
    TextView tues_first_from,tues_first_to,tues_second_from,tues_second_to;
    TextView wed_first_from,wed_first_to,wed_second_from,wed_second_to;
    TextView thurs_first_from,thurs_first_to,thurs_second_from,thurs_second_to;
    TextView fri_first_from,fri_first_to,fri_second_from,fri_second_to;

    TextView selectedTimeTxT;



    ImageView logo,license_img,id_img,selected_img;
    File logo_file,license_file,id_file,selected_file;
    TextView market_name,market_name_ar,market_desc,market_desc_ar,market_address,market_address_ar,market_email,market_password,market_phone,owner_name;
    TextView manager_name,manager_work,manager_phone,license,id_number,bank_name,account_number,order_limit,delivery_fees;
    CheckBox accepted;
    Button click_btn;



    RecyclerView listMain;
    Spinner citiesSpinner;
    Spinner districtSpinner;
    ArrayList<String> listTypes;
    ArrayList<String> listDistrict;
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
        setContentView(R.layout.registration_page);
        setupDateView();
        findViewById(R.id.mapicon).setOnClickListener(view -> startActivity(new Intent(SignUpActivity.this,ChooseMapLocation.class)));
        citiesSpinner = findViewById(R.id.cities);
        districtSpinner = findViewById(R.id.district);
        order_limit = findViewById(R.id.order_limit);
        delivery_fees = findViewById(R.id.delivery_price);
        market_name_ar = findViewById(R.id.market_name_ar);
        market_desc = findViewById(R.id.market_desc);
        market_desc_ar = findViewById(R.id.market_desc_ar);
        market_address_ar = findViewById(R.id.market_address_ar);
        market_password = findViewById(R.id.password);
        logo = findViewById(R.id.logo);
        license_img = findViewById(R.id.license_img);
        id_img = findViewById(R.id.id_img);
        market_name = findViewById(R.id.market_name);
        market_address = findViewById(R.id.market_address);
        market_email = findViewById(R.id.market_email);
        market_phone = findViewById(R.id.market_phone);
        owner_name = findViewById(R.id.owner_name);
        manager_name = findViewById(R.id.manager_name);
        manager_work = findViewById(R.id.manager_work);
        manager_phone = findViewById(R.id.manager_phone);
        license = findViewById(R.id.license);
        id_number = findViewById(R.id.id_number);
        bank_name = findViewById(R.id.bank_name);
        account_number = findViewById(R.id.account_number);
        accepted = findViewById(R.id.accepted);
        click_btn = findViewById(R.id.click_btn);
        click_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( SignUpActivity.this, new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult instanceIdResult) {
                        String newToken = instanceIdResult.getToken();
                        MakeRequest(newToken);
                        MyFirebaseInstanceIDService service = new MyFirebaseInstanceIDService();
                        service.onNewToken(newToken);
                    }
                });
            }
        });


        isStoragePermissionGranted();
        isCameraGranted();

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_file = logo_file;
                selected_img = logo;
                setDialog();
            }
        });
        license_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_file = license_file;
                selected_img = license_img;
                setDialog();
            }
        });
        id_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_file = id_file;
                selected_img = id_img;
                setDialog();
            }
        });


        listMain = findViewById(R.id.list);
        listMain.setNestedScrollingEnabled(true);
        listMain.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listMain.setLayoutManager(layoutManager);
        CheckListAdapter adapter = new CheckListAdapter(this,new ArrayList<FoodMainItem>());
        listMain.setAdapter(adapter);

        ((RadioGroup)findViewById(R.id.group)).setOnCheckedChangeListener((radioGroup, i) -> {
            if (radioGroup.getCheckedRadioButtonId() == R.id.restaurant)
                GetCategories();
            else if (radioGroup.getCheckedRadioButtonId() == R.id.markets)
                GetMarkets();
            else
                GetShabra();

        });

        citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                GetDistrictsById(arrListCities.get(citiesSpinner.getSelectedItemPosition()).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        GetAllCities();
    }
    void GetAllCities() {
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.AUTH_URL);
        storeServices.GetALLCities()
                .enqueue(new Callback<GetCitiesMainModel>() {
                    @Override
                    public void onResponse(@NonNull Call<GetCitiesMainModel> call,
                                           @NonNull Response<GetCitiesMainModel> response) {
                        listTypes = new ArrayList<>();
                        arrListCities = response.body().getCities();
                        for (int i = 0 ; i <response.body().getCities().size();i++)
                            listTypes.add(response.body().getCities().get(i).getName());
                        ArrayAdapter dataAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,listTypes);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        citiesSpinner.setAdapter(dataAdapter);
                    }
                    @Override
                    public void onFailure(@NonNull Call<GetCitiesMainModel> call, @NonNull Throwable t) {
                        DismissDialogView();
                        Toast.makeText(SignUpActivity.this,"failed  .. try again",Toast.LENGTH_LONG).show();
                    }
                });
    }
    void GetDistrictsById(int i) {
        ShowDialogView();
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.AUTH_URL);
        storeServices.DistrictsById(String.valueOf(i))
                .enqueue(new Callback<GetDistrictsMainModel>() {
                    @Override
                    public void onResponse(@NonNull Call<GetDistrictsMainModel> call,
                                           @NonNull Response<GetDistrictsMainModel> response) {
                        DismissDialogView();
                        listDistrict = new ArrayList<>();
                        for (int i = 0 ; i <response.body().getDistricts().size();i++)
                            listDistrict.add(response.body().getDistricts().get(i).getName());
                        ArrayAdapter dataAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,listDistrict);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        districtSpinner.setAdapter(dataAdapter);
                    }
                    @Override
                    public void onFailure(@NonNull Call<GetDistrictsMainModel> call, @NonNull Throwable t) {
                        DismissDialogView();
                        Toast.makeText(SignUpActivity.this,"failed  .. try again",Toast.LENGTH_LONG).show();
                    }
                });
    }



    void GetCategories(){
        ShowDialogView();
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.MAIN_URL);
        storeServices.GetAllCategories()
                .enqueue(new Callback<FoodMainItemResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<FoodMainItemResponse> call,
                                           @NonNull Response<FoodMainItemResponse> response) {
                        DismissDialogView();
                        CheckListAdapter adapter = new CheckListAdapter(getApplicationContext(),response.body().getMainCategories());
                        listMain.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<FoodMainItemResponse> call, @NonNull Throwable t) {
                        DismissDialogView();
                        Log.e("failederror",t.getMessage());
                        Toast.makeText(SignUpActivity.this,"failed  ... try again",Toast.LENGTH_LONG).show();
                    }
                });
    }
    void GetMarkets(){
        ShowDialogView();
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.MAIN_URL2);
        storeServices.GetAllMarketsTypes()
                .enqueue(new Callback<FoodMainItemResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<FoodMainItemResponse> call,
                                           @NonNull Response<FoodMainItemResponse> response) {
                        DismissDialogView();
                        CheckListAdapter adapter = new CheckListAdapter(getApplicationContext(),response.body().getItems());
                        listMain.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<FoodMainItemResponse> call, @NonNull Throwable t) {
                        DismissDialogView();
                        Toast.makeText(SignUpActivity.this,"failed  .. try again",Toast.LENGTH_LONG).show();
                    }
                });
    }
    void GetShabra(){
        ShowDialogView();
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.MAIN_URL2);
        storeServices.GetAllShabraTypes()
                .enqueue(new Callback<FoodMainItemResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<FoodMainItemResponse> call,
                                           @NonNull Response<FoodMainItemResponse> response) {
                        DismissDialogView();
                        CheckListAdapter adapter = new CheckListAdapter(getApplicationContext(),response.body().getItems());
                        listMain.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<FoodMainItemResponse> call, @NonNull Throwable t) {
                        DismissDialogView();
                        Log.e("terror",t.getMessage());
                        Toast.makeText(SignUpActivity.this,"failed  .. try again",Toast.LENGTH_LONG).show();
                    }
                });
    }

    void MakeRequest(String token){

        ShowDialogView();
        RequestRegister request = new RequestRegister();
        request.setName(market_name.getText().toString());
        request.setName_ar(market_name_ar.getText().toString());
        request.setName_en(market_name.getText().toString());

        request.setAddress(market_address_ar.getText().toString());
        request.setAddress_en(market_address.getText().toString());

        request.setDescription(market_desc_ar.getText().toString());
        request.setDescription_en(market_desc.getText().toString());


        request.setPlace_email(market_email.getText().toString());
        request.setPassword(market_password.getText().toString());


        request.setPlace_owner_name(market_address_ar.getText().toString());
        request.setOrder_limit(order_limit.getText().toString());
        request.setDelivery_price(delivery_fees.getText().toString());
        request.setPlace_phone(market_phone.getText().toString());


//        request.setBankName(bank_name.getText().toString());
//        request.setBankNumber(account_number.getText().toString());

        request.setCert(license.getText().toString());
        request.setSignatur(id_number.getText().toString());

        request.setImgcert(license_file);
        request.setSignatureimage(id_file);
        request.setImagere(logo_file);
        responsiblesModel model = new responsiblesModel();
        model.setName(manager_name.getText().toString());
        model.setJob(manager_work.getText().toString());
        model.setPhone(manager_phone.getText().toString());
        request.getResponsibles().add(model);


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
                        SharedPrefDB sharedPrefDB = new SharedPrefDB(SignUpActivity.this);
                        sharedPrefDB.cacheShopData(SignUpActivity.this, response.body());
                        startActivity(new Intent(SignUpActivity.this,AllCategories.class));
                        finish();
                    }

                    @Override
                    public void onFailure(@NonNull Call<MainLoginResponse> call, @NonNull Throwable t) {
                        DismissDialogView();
                        Toast.makeText(SignUpActivity.this,"failed  .. try again",Toast.LENGTH_LONG).show();
                    }
                });
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



    public void isStoragePermissionGranted() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Log.v("DataIs", "Permission: GRANED");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    101);
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Log.v("DataIs", "Permission: STORAGE");
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        101);
                Log.v("DataIs", "Permission: STORAGE HERE");
            }
        } else {
            Log.v("DataIs", "Permission: STORAGEHHH");
        }
    }
    public void isCameraGranted() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            Log.v("DataIs", "Permission: GRANED");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    122);
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                Log.v("DataIs", "Permission: STORAGE");
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        122);
                Log.v("DataIs", "Permission: STORAGE HERE");
            }
        } else {
            Log.v("DataIs", "Permission: STORAGEHHH");
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v("DataIs","Permission: "+permissions[0]+ "was "+grantResults[0]);
        }
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 22:
                    Uri selectedImage = data.getData();
                    selected_file =  new File(getPath(selectedImage));
                    selected_img.setImageURI(selectedImage);
                    break;
                case 33:
                    selected_file =  new File(url_to_save);
                    Glide.with(this).load(url_to_save).into(selected_img);
                    break;
            }

    }

    private void setDialog() {
        final Dialog dialog = new Dialog(this, R.style.Theme_Dialog);
        dialog.setCancelable(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.choose_gallery_or_camera_dialog);
        final LinearLayout gallery_container = dialog.findViewById(R.id.gallery_container);
        final LinearLayout camera_container = dialog.findViewById(R.id.camera_container);
        final LinearLayout cancel_container = dialog.findViewById(R.id.cancel_container);

        cancel_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        gallery_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                pickFromGallery();
            }
        });
        camera_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                captureFromCamera();
            }
        });

        final Window dialogWindow = dialog.getWindow();
        final WindowManager.LayoutParams dialogWindowAttributes = dialogWindow.getAttributes();
        // Set fixed width (280dp) and WRAP_CONTENT height
        final WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogWindowAttributes);
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.BOTTOM;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }


    private void pickFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(intent, 22);
    }

    private void captureFromCamera() {
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getApplicationContext(), "com.superbekala.android.userapp.provider", createImageFile()));
            startActivityForResult(intent, 33);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        url_to_save = image.getAbsolutePath();
        return image;
    }
    String url_to_save;
    private String getPath(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

}

