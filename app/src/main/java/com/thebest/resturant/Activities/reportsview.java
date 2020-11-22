package com.thebest.resturant.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.thebest.resturant.Adapters.AllOrdersAdapter;
import com.thebest.resturant.Adapters.FoodShopCategoryAdapter;
import com.thebest.resturant.Models.Requests.RequestResturantReports;
import com.thebest.resturant.Models.Responses.MyMenusItemModel;
import com.thebest.resturant.Models.Responses.MyMenusMainResponse;
import com.thebest.resturant.Models.Responses.MainResponseOrders;
import com.thebest.resturant.Models.Responses.CurrnetTrip;
import com.thebest.resturant.Models.Responses.OrderItemsModel;
import com.thebest.resturant.Models.from_to_date;
import com.thebest.resturant.Networking.ServiceGenerator;
import com.thebest.resturant.Networking.StoreAPIInterface;
import com.thebest.resturant.R;
import com.thebest.resturant.Utilities.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class reportsview extends AppCompatActivity {
    Spinner datesSpinner;
    RecyclerView list_orders;
    TextView total_orders,money_orders,completed,canceled;

    private ArrayList<CurrnetTrip> arrlist = new ArrayList<>();
    public static String getCalculatedDate(int days) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.DAY_OF_YEAR, days * -1);
        return s.format(new Date(cal.getTimeInMillis()));
    }
    public static String getCurrentdDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        return s.format(new Date(cal.getTimeInMillis()));
    }

    LinearLayout containerfromto;
    TextView from,to;
    ImageView filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reports_view);
        datesSpinner = findViewById(R.id.spinner);
        filter = findViewById(R.id.filter);
        to = findViewById(R.id.to);
        from = findViewById(R.id.from);
        containerfromto = findViewById(R.id.containerfromto);
        String[] listTypes = new String[]{"Day","Week","Month","Range"};
        final Integer[] listDays = new Integer[]{0,7,30,0};
        ArrayAdapter dataAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listTypes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        datesSpinner.setAdapter(dataAdapter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MakeRequest(from.getText().toString(), to.getText().toString());
            }
        });
        datesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 3){
                    containerfromto.setVisibility(View.VISIBLE);
                }
                else {
                    containerfromto.setVisibility(View.GONE);
                    MakeRequest(getCalculatedDate(listDays[i]), getCurrentdDate());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        completed = findViewById(R.id.completed);
        canceled = findViewById(R.id.canceled);
        total_orders = findViewById(R.id.total_orders);
        money_orders = findViewById(R.id.money_orders);


        list_orders = findViewById(R.id.list_orders);
        list_orders.setNestedScrollingEnabled(true);
        list_orders.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        list_orders.setLayoutManager(layoutManager);
        AllOrdersAdapter adapter = new AllOrdersAdapter(this,arrlist,false);
        list_orders.setAdapter(adapter);



        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (clicked.equals("from"))
                    from.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
                else
                    to.setText(dayOfMonth + "-" + monthOfYear + "-" + year);

            }
        };

        from.setOnClickListener(view -> {
            clicked = "from";
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            DatePickerDialog dialog = new DatePickerDialog(reportsview.this, R.style.DialogThemeMonthly, date,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();

        });
        to.setOnClickListener(view -> {
            clicked = "to";
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            DatePickerDialog dialog = new DatePickerDialog(reportsview.this, R.style.DialogThemeMonthly, date,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();

        });
        from.setText(getCurrentdDate());
        to.setText(getCurrentdDate());


    }
    String clicked = "";
    private void getEntries() {

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
    void MakeRequest(String from ,String to){
        ShowDialogView();
        RequestResturantReports item = new RequestResturantReports();
        item.setFrom_date(from);
        item.setTo_date(to);
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.MAIN_URL);
        storeServices.GetReports(item)
                .enqueue(new Callback<MainResponseOrders>() {
                    @Override
                    public void onResponse(@NonNull Call<MainResponseOrders> call,
                                           @NonNull Response<MainResponseOrders> response) {
                        DismissDialogView();
                        arrlist = response.body().getMyOrders();
                        AllOrdersAdapter adapter = new AllOrdersAdapter(getApplicationContext(),arrlist,false);
                        list_orders.setAdapter(adapter);
                        total_orders.setText(response.body().getMyOrdersCount()+"");
                        money_orders.setText(response.body().getMyOrdersMoney()+"");
                        completed.setText(response.body().getMyOrdersDone()+"");
                        canceled.setText(response.body().getMyOrdersCanceled()+"");
                    }

                    @Override
                    public void onFailure(@NonNull Call<MainResponseOrders> call, @NonNull Throwable t) {
                        DismissDialogView();
                        Log.e("ThrowableThrowable","nooo");
                        Toast.makeText(reportsview.this,"failed  .. try again",Toast.LENGTH_LONG).show();
                    }
                });
    }

}