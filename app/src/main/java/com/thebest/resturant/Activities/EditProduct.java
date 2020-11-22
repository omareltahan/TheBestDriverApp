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

import com.bumptech.glide.Glide;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thebest.resturant.Adapters.FoodShopVariationAdapter;
import com.thebest.resturant.AppController;
import com.thebest.resturant.Models.Requests.AddMenuItem;
import com.thebest.resturant.Models.Requests.AddProductRequest;
import com.thebest.resturant.Models.Requests.RequestRegister;
import com.thebest.resturant.Models.Responses.FoodShopsCategoriesProductsitem;
import com.thebest.resturant.Models.Responses.FoodShopsItemAttributes;
import com.thebest.resturant.Networking.ServiceGenerator;
import com.thebest.resturant.Networking.StoreAPIInterface;
import com.thebest.resturant.R;
import com.thebest.resturant.Utilities.Constant;
import com.thebest.resturant.Utilities.SharedPrefDB;
import com.thebest.resturant.listenToDeleteVariation;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProduct extends AppCompatActivity implements listenToDeleteVariation {
    ArrayList<FoodShopsItemAttributes> model,model2,model3,modeladditional;
    FoodShopsCategoriesProductsitem mItem;
    TextView title,desc,title_ar,desc_ar,price;
    ImageView imageView;
    TextView titleVariation,titleVariation2,titleVariation3;
    LinearLayout variationContainer,variationContainer2,variationContainer3,variationContainerplus;
    RecyclerView list_variation,list_variation2,list_variation3,list_variationplus;
    File file;
    int CountOfVariationsShown = 0;
    ImageView AddVariationTitle,AddAdditionalTitle;
    ImageView AddVariationRowOfFirst;
    ImageView AddVariationRowOfSecond;
    ImageView AddVariationRowOfThird;
    ImageView AddVariationRowOfAdditional;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_product_view);
        model = new ArrayList<>();
        model2 = new ArrayList<>();
        model3 = new ArrayList<>();
        modeladditional = new ArrayList<>();
        AddVariationTitle = findViewById(R.id.AddVariationTitle);
        AddAdditionalTitle = findViewById(R.id.AddAdditionalTitle);
        AddVariationRowOfFirst = findViewById(R.id.AddVariationRowOfFirst);
        AddVariationRowOfSecond = findViewById(R.id.AddVariationRowOfSecond);
        AddVariationRowOfThird = findViewById(R.id.AddVariationRowOfThird);
        AddVariationRowOfAdditional = findViewById(R.id.AddVariationRowOfAdditional);

        variationContainer = findViewById(R.id.variationContainer);
        titleVariation = findViewById(R.id.titleVariation);
        variationContainer2 = findViewById(R.id.variationContainer2);
        titleVariation2 = findViewById(R.id.titleVariation2);
        variationContainer3 = findViewById(R.id.variationContainer3);
        titleVariation3 = findViewById(R.id.titleVariation3);
        variationContainerplus = findViewById(R.id.variationContainerplus);



        AddVariationTitle.setOnClickListener(view -> {
            if (CountOfVariationsShown == 0)
                variationContainer.setVisibility(View.VISIBLE);
            else if (CountOfVariationsShown == 1)
                variationContainer2.setVisibility(View.VISIBLE);
            else {
                AddVariationTitle.setEnabled(false);
                AddVariationTitle.setAlpha(0.5f);
                variationContainer3.setVisibility(View.VISIBLE);
            }
        });
        AddAdditionalTitle.setOnClickListener(view -> {
            AddAdditionalTitle.setEnabled(false);
            AddAdditionalTitle.setAlpha(0.5f);
            variationContainerplus.setVisibility(View.VISIBLE);
        });

        AddVariationRowOfFirst.setOnClickListener(view -> {
            FoodShopVariationAdapter adapterlist = new FoodShopVariationAdapter(getApplicationContext(), model,true,this,1);
            AddVariationDialog(adapterlist , model , list_variation);
        });
        AddVariationRowOfSecond.setOnClickListener(view -> {
            FoodShopVariationAdapter adapterlist = new FoodShopVariationAdapter(getApplicationContext(), model2,true,this,2);
            AddVariationDialog(adapterlist , model2 , list_variation2);
        });
        AddVariationRowOfThird.setOnClickListener(view -> {
            FoodShopVariationAdapter adapterlist = new FoodShopVariationAdapter(getApplicationContext(), model3,true,this,3);
            AddVariationDialog(adapterlist , model3 , list_variation3);
        });
        AddVariationRowOfAdditional.setOnClickListener(view -> {
            modeladditional.add(new FoodShopsItemAttributes());
            FoodShopVariationAdapter adapterlist = new FoodShopVariationAdapter(getApplicationContext(), modeladditional,true,this,4);
            AddVariationDialog(adapterlist , modeladditional , list_variationplus);
        });

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

        title = findViewById(R.id.title);
        title_ar = findViewById(R.id.title_ar);
        desc_ar = findViewById(R.id.desc_ar);
        desc = findViewById(R.id.desc);
        price = findViewById(R.id.price);
        imageView = findViewById(R.id.image);
        variationContainer = findViewById(R.id.variationContainer);
        titleVariation = findViewById(R.id.titleVariation);

        if ( getIntent().getExtras()!= null) {
            mItem = (FoodShopsCategoriesProductsitem) getIntent().getExtras().getSerializable("data");
            title_ar.setText(mItem.getName());
            title.setText(mItem.getName_en());
            desc_ar.setText(mItem.getDescription());
            desc.setText(mItem.getDescription_en());
            price.setText(mItem.getPrice() + "");
            Glide.with(this).load(mItem.getImage()).into(imageView);
            model = new Gson().fromJson((mItem.getItemattributes().toString()), new TypeToken<List<FoodShopsItemAttributes>>(){}.getType());
            model2 = new Gson().fromJson((mItem.getItemattributes_two().toString()), new TypeToken<List<FoodShopsItemAttributes>>(){}.getType());
            model3 = new Gson().fromJson((mItem.getItemattributes_third().toString()), new TypeToken<List<FoodShopsItemAttributes>>(){}.getType());
            modeladditional = new Gson().fromJson((mItem.getAdditionalAttributes().toString()), new TypeToken<List<FoodShopsItemAttributes>>(){}.getType());

            if (model.size()>0){
                variationContainer.setVisibility(View.VISIBLE);
                titleVariation.setText(mItem.getAttribute_title()+"-"+mItem.getAttribute_title_en());
                FoodShopVariationAdapter adapterlist = new FoodShopVariationAdapter(this,model,true,this,1);
                list_variation.setAdapter(adapterlist);
            }
            else {
                variationContainer.setVisibility(View.GONE);
            }
            if (model2.size()>0){
                variationContainer2.setVisibility(View.VISIBLE);
                titleVariation2.setText(mItem.getAttribute_title_two()+"-"+mItem.getAttribute_title_en_two());
                FoodShopVariationAdapter adapterlist = new FoodShopVariationAdapter(this,model2,true,this,2);
                list_variation2.setAdapter(adapterlist);
            }
            if (model3.size()>0){
                variationContainer3.setVisibility(View.VISIBLE);
                titleVariation3.setText(mItem.getAttribute_title_two()+"-"+mItem.getAttribute_title_en_third());
                FoodShopVariationAdapter adapterlist = new FoodShopVariationAdapter(this,model3,true,this,3);
                list_variation3.setAdapter(adapterlist);
            }
            if (modeladditional.size()>0){
                variationContainerplus.setVisibility(View.VISIBLE);
                FoodShopVariationAdapter adapterlist = new FoodShopVariationAdapter(this, modeladditional,true,this,4);
                list_variationplus.setAdapter(adapterlist);
            }
        }

        findViewById(R.id.back).setOnClickListener(view -> finish());


        isStoragePermissionGranted();
        isCameraGranted();
        imageView.setOnClickListener(view -> setDialog());


        findViewById(R.id.click_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MakeRequest();
            }
        });

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
                    file =  new File(getPath(selectedImage));
                    imageView.setImageURI(selectedImage);
                    break;
                case 33:
                    Glide.with(this).load(url_to_save).into(imageView);
                    break;
            }

    }


    void MakeRequest() {
        ShowDialogView();
        AddProductRequest request = new AddProductRequest();
        if (getIntent().getExtras()!=null)
            request.setMenu_category_id(String.valueOf(mItem.getId()));
        request.setName_ar(title_ar.getText().toString());
        request.setName_en(title.getText().toString());
        request.setDescription_ar(desc_ar.getText().toString());
        request.setDescription_en(desc.getText().toString());
        request.setPrice(price.getText().toString());
        request.setImage(file);

        request.setAttribute_title(titleVariation.getText().toString());
        request.setAttribute_title_en(titleVariation.getText().toString());

        request.setAttribute_title_en_two(titleVariation.getText().toString());
        request.setAttribute_title_en_two(titleVariation.getText().toString());

        request.setAttribute_title_third(titleVariation.getText().toString());
        request.setAttribute_title_en_third(titleVariation.getText().toString());

        request.setAttribute_title(titleVariation.getText().toString());
        request.setAttribute_title_en(titleVariation.getText().toString());


        request.setItemattributes(new Gson().toJson(model));
        request.setItemattributes_two(new Gson().toJson(model2));
        request.setItemattributes_third(new Gson().toJson(model3));
        request.setAdditionalAttributes(new Gson().toJson(modeladditional));

        request.setRestaurant_id(String.valueOf(AppController.shopModel.getId()));
        request.setCat_id(String.valueOf(AppController.categoryModel.getId()));
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.MAIN_URL);
        storeServices.AddProduct(request)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call,
                                           @NonNull Response<String> response) {

                    }

                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                        DismissDialogView();
                        Toast.makeText(EditProduct.this,"failed  .. try again",Toast.LENGTH_LONG).show();
                    }
                });
    }
    public void AddVariationDialog(FoodShopVariationAdapter adapter , ArrayList<FoodShopsItemAttributes> list , RecyclerView recyclerView){
        Dialog dialog_of_category = new Dialog(this,R.style.Theme_Dialog);
        dialog_of_category.setCancelable(true);
        dialog_of_category.setCanceledOnTouchOutside(false);
        dialog_of_category.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_of_category.setContentView(R.layout.add_variation_item);
        final TextView name = dialog_of_category.findViewById(R.id.name);
        final TextView namear = dialog_of_category.findViewById(R.id.namear);
        final TextView price = dialog_of_category.findViewById(R.id.price);
        TextView btn = dialog_of_category.findViewById(R.id.btn_click);
        imageView.setOnClickListener(view -> setDialog());
        btn.setOnClickListener(view -> {
            FoodShopsItemAttributes modelval = new FoodShopsItemAttributes();
            modelval.setName_en(name.getText().toString());
            modelval.setName_ar(namear.getText().toString());
            modelval.setPrice(price.getText().toString());
            list.add(modelval);
            recyclerView.setAdapter(adapter);
            dialog_of_category.dismiss();
        });
        final Window dialogWindow = dialog_of_category.getWindow();
        final WindowManager.LayoutParams dialogWindowAttributes = dialogWindow.getAttributes();
        final WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogWindowAttributes);
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity =  Gravity.CENTER;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
        dialog_of_category.show();
    }


    @Override
    public void deleteVariation(int position, int type) {
        if (type == 1){
            model.remove(position);
            FoodShopVariationAdapter adapter = new FoodShopVariationAdapter(getApplicationContext(), model,true,this,1);
            list_variation.setAdapter(adapter);
        }
        else if (type == 2){
            model2.remove(position);
            FoodShopVariationAdapter adapter2 = new FoodShopVariationAdapter(getApplicationContext(), model2,true,this,2);
            list_variation2.setAdapter(adapter2);
        }
        else if (type == 3){
            model2.remove(position);
            FoodShopVariationAdapter adapter3 = new FoodShopVariationAdapter(getApplicationContext(), model3,true,this,3);
            list_variation3.setAdapter(adapter3);
        }
        else {
            modeladditional.remove(position);
            FoodShopVariationAdapter adapter3 = new FoodShopVariationAdapter(getApplicationContext(), modeladditional,true,this,4);
            list_variationplus.setAdapter(adapter3);
        }
    }
}