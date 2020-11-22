package com.thebest.resturant.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thebest.resturant.Adapters.FoodShopVariationAdapter;
import com.thebest.resturant.Models.Responses.FoodShopsCategoriesProductsitem;
import com.thebest.resturant.Models.Responses.FoodShopsItemAttributes;
import com.thebest.resturant.Models.WorkingHoursModel;
import com.thebest.resturant.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails extends AppCompatActivity {
    FoodShopsCategoriesProductsitem mItem;
    TextView title,title_ar,desc,desc_ar,price;
    ImageView imageView;
    TextView titleVariation,titleVariation2,titleVariation3;
    LinearLayout variationContainer,variationContainer2,variationContainer3,variationContainerplus;
    RecyclerView list_variation,list_variation2,list_variation3,list_variationplus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resturant_product_view);
        variationContainer = findViewById(R.id.variationContainer);
        titleVariation = findViewById(R.id.titleVariation);
        variationContainer2 = findViewById(R.id.variationContainer2);
        titleVariation2 = findViewById(R.id.titleVariation2);
        variationContainer3 = findViewById(R.id.variationContainer3);
        titleVariation3 = findViewById(R.id.titleVariation3);
        variationContainerplus = findViewById(R.id.variationContainerplus);



        list_variation = findViewById(R.id.list_variation);
        list_variation.setNestedScrollingEnabled(true);
        list_variation.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        list_variation.setLayoutManager(layoutManager);

        list_variation2 = findViewById(R.id.list_variation2);
        list_variation2.setNestedScrollingEnabled(true);
        list_variation2.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        list_variation2.setLayoutManager(layoutManager2);

        list_variation3 = findViewById(R.id.list_variation3);
        list_variation3.setNestedScrollingEnabled(true);
        list_variation3.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        list_variation3.setLayoutManager(layoutManager3);

        list_variationplus = findViewById(R.id.list_variationplus);
        list_variationplus.setNestedScrollingEnabled(true);
        list_variationplus.setHasFixedSize(true);
        LinearLayoutManager layoutManagerplus = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        list_variationplus.setLayoutManager(layoutManagerplus);


        mItem= (FoodShopsCategoriesProductsitem) getIntent().getExtras().getSerializable("data");
        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        title_ar = findViewById(R.id.title_ar);
        desc_ar = findViewById(R.id.desc_ar);
        price = findViewById(R.id.price);
        imageView = findViewById(R.id.image);

        title.setText(mItem.getName_en());
        title_ar.setText(mItem.getName());
        desc.setText(mItem.getDescription());
        desc_ar.setText(mItem.getDescription());
        price.setText(mItem.getPrice()+getResources().getString(R.string.kwd));
        Glide.with(this).load(mItem.getImage()).into(imageView);
        findViewById(R.id.back).setOnClickListener(view -> finish());
        ArrayList<FoodShopsItemAttributes> model = new Gson().fromJson((mItem.getItemattributes().toString()), new TypeToken<List<FoodShopsItemAttributes>>(){}.getType());
        ArrayList<FoodShopsItemAttributes> model2 = new Gson().fromJson((mItem.getItemattributes_two().toString()), new TypeToken<List<FoodShopsItemAttributes>>(){}.getType());
        ArrayList<FoodShopsItemAttributes> model3 = new Gson().fromJson((mItem.getItemattributes_third().toString()), new TypeToken<List<FoodShopsItemAttributes>>(){}.getType());
        ArrayList<FoodShopsItemAttributes> modeladditional = new Gson().fromJson((mItem.getAdditionalAttributes().toString()), new TypeToken<List<FoodShopsItemAttributes>>(){}.getType());

        if (model.size()>0){
            variationContainer.setVisibility(View.VISIBLE);
            titleVariation.setText(mItem.getAttribute_title()+"-"+mItem.getAttribute_title_en());
            FoodShopVariationAdapter adapterlist = new FoodShopVariationAdapter(this,model,false , null,1);
            list_variation.setAdapter(adapterlist);
        }
        else {
            variationContainer.setVisibility(View.GONE);
        }
        if (model2.size()>0){
            variationContainer2.setVisibility(View.VISIBLE);
            titleVariation2.setText(mItem.getAttribute_title_two()+"-"+mItem.getAttribute_title_en_two());
            FoodShopVariationAdapter adapterlist = new FoodShopVariationAdapter(this,model2,false, null,2);
            list_variation2.setAdapter(adapterlist);
        }
        if (model3.size()>0){
            variationContainer3.setVisibility(View.VISIBLE);
            titleVariation3.setText(mItem.getAttribute_title_two()+"-"+mItem.getAttribute_title_en_third());
            FoodShopVariationAdapter adapterlist = new FoodShopVariationAdapter(this,model3,false, null,3);
            list_variation3.setAdapter(adapterlist);
        }
        if (modeladditional.size()>0){
            variationContainerplus.setVisibility(View.VISIBLE);
            FoodShopVariationAdapter adapterlist = new FoodShopVariationAdapter(this, modeladditional,false, null,4);
            list_variationplus.setAdapter(adapterlist);
        }

    }
}