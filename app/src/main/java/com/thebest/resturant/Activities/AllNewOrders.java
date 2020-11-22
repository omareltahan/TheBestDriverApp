package com.thebest.resturant.Activities;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.thebest.resturant.Adapters.AllOrdersAdapter;
import com.thebest.resturant.Models.Responses.CurrnetTrip;
import com.thebest.resturant.Models.Responses.OldOrdersMainResponse;
import com.thebest.resturant.Networking.ServiceGenerator;
import com.thebest.resturant.Networking.StoreAPIInterface;
import com.thebest.resturant.R;
import com.thebest.resturant.Utilities.Constant;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllNewOrders extends AppCompatActivity {
    private ArrayList<CurrnetTrip> arrlist = new ArrayList<>();
    RecyclerView list_orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_orders);

        list_orders = findViewById(R.id.list);
        list_orders.setNestedScrollingEnabled(true);
        list_orders.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        list_orders.setLayoutManager(layoutManager);
        AllOrdersAdapter adapter = new AllOrdersAdapter(this,arrlist,true);
        list_orders.setAdapter(adapter);
        MakeRequest();
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
    void MakeRequest(){
        ShowDialogView();
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.MAIN_URL);
        storeServices.ShowNewOrders()
                .enqueue(new Callback<OldOrdersMainResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<OldOrdersMainResponse> call,
                                           @NonNull Response<OldOrdersMainResponse> response) {
                        DismissDialogView();
                        arrlist = response.body().getNewOrders();
                        AllOrdersAdapter adapter = new AllOrdersAdapter(getApplicationContext(),arrlist,true);
                        list_orders.setAdapter(adapter);
                    }
                    @Override
                    public void onFailure(@NonNull Call<OldOrdersMainResponse> call, @NonNull Throwable t) {
                        DismissDialogView();
                        Toast.makeText(AllNewOrders.this,"No orders exist",Toast.LENGTH_LONG).show();
                    }
                });
    }

}