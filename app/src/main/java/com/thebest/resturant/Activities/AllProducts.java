package com.thebest.resturant.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.thebest.resturant.Adapters.FoodProductAdapter;
import com.thebest.resturant.Models.Responses.FoodShopsCategoriesProductsResponse;
import com.thebest.resturant.Models.Responses.FoodShopsCategoriesProductsitem;
import com.thebest.resturant.Models.Responses.MyMenusItemModel;
import com.thebest.resturant.Networking.ServiceGenerator;
import com.thebest.resturant.Networking.StoreAPIInterface;
import com.thebest.resturant.R;
import com.thebest.resturant.Utilities.Constant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class AllProducts extends AppCompatActivity {
    private List<FoodShopsCategoriesProductsitem> ShopsList = new ArrayList<>();

    RecyclerView listMain;
    ImageView back;
    MyMenusItemModel item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_add);
        findViewById(R.id.back).setOnClickListener(view -> finish());
        findViewById(R.id.addplus).setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), EditProduct.class);
            startActivity(intent);
        });
        findViewById(R.id.drawer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllProducts.this,DrawerView.class));
            }
        });

        item = (MyMenusItemModel) getIntent().getExtras().getSerializable("item");
        back = findViewById(R.id.back);
        back.setOnClickListener(view -> AllProducts.this.finish());
        listMain = findViewById(R.id.list);
        listMain.setNestedScrollingEnabled(true);
        listMain.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listMain.setLayoutManager(layoutManager);
        FoodProductAdapter adapterlist = new FoodProductAdapter(this,ShopsList);
        listMain.setAdapter(adapterlist);

        MakeRequestShops(item.getId());
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

    void MakeRequestShops(int id){
        ShowDialogView();
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.MAIN_URL);
        storeServices.GetMenuItems(id)
                .enqueue(new Callback<FoodShopsCategoriesProductsResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<FoodShopsCategoriesProductsResponse> call,
                                           @NonNull Response<FoodShopsCategoriesProductsResponse> response) {
                        DismissDialogView();
                        ShopsList = response.body().getRestaurantMenu();
                        FoodProductAdapter adapter = new FoodProductAdapter(getApplicationContext(),ShopsList);
                        listMain.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<FoodShopsCategoriesProductsResponse> call, @NonNull Throwable t) {
                        DismissDialogView();
                        Log.e("FoodShopsCategories",t.getMessage());
                        Toast.makeText(AllProducts.this,"failed  .. try again",Toast.LENGTH_LONG).show();
                    }
                });
    }
}