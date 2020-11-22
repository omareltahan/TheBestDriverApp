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
import com.thebest.resturant.Adapters.FoodShopCategoryAdapter;
import com.thebest.resturant.Models.MenuModel;
import com.thebest.resturant.Models.Requests.AddMenuItem;
import com.thebest.resturant.Models.Requests.RequestLogin;
import com.thebest.resturant.Models.Responses.FoodShopsItemAttributes;
import com.thebest.resturant.Models.Responses.MainLoginResponse;
import com.thebest.resturant.Models.Responses.MyMenusItemModel;
import com.thebest.resturant.Models.Responses.MyMenusMainResponse;
import com.thebest.resturant.Networking.ServiceGenerator;
import com.thebest.resturant.Networking.StoreAPIInterface;
import com.thebest.resturant.R;
import com.thebest.resturant.Utilities.Constant;
import com.thebest.resturant.Utilities.SharedPrefDB;
import com.thebest.resturant.listenToOpenEdit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCategories extends AppCompatActivity implements listenToOpenEdit {
    private List<MyMenusItemModel> arrlist = new ArrayList<>();
    RecyclerView listMain;
    FoodShopCategoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_add);
        findViewById(R.id.addplus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCategoryDialog(-1);
            }
        });
        findViewById(R.id.drawer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllCategories.this,DrawerView.class));
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        listMain = findViewById(R.id.list);
        listMain.setNestedScrollingEnabled(true);
        listMain.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listMain.setLayoutManager(layoutManager);
        adapter = new FoodShopCategoryAdapter(this,arrlist,this);
        listMain.setAdapter(adapter);
        MakeRequest();
    }

    void MakeRequest(){
        ShowDialogView();
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.MAIN_URL);
        storeServices.ShowMyMenus()
                .enqueue(new Callback<MyMenusMainResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MyMenusMainResponse> call,
                                           @NonNull Response<MyMenusMainResponse> response) {
                        DismissDialogView();
                        arrlist = response.body().getMyMenus();
                        FoodShopCategoryAdapter adapter = new FoodShopCategoryAdapter(getApplicationContext(),arrlist,AllCategories.this);
                        listMain.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<MyMenusMainResponse> call, @NonNull Throwable t) {
                        DismissDialogView();
                        Toast.makeText(AllCategories.this,"failed  .. try again",Toast.LENGTH_LONG).show();
                    }
                });
    }

    File file;

    Dialog dialog_of_category;
    public void AddCategoryDialog(final int position){
        dialog_of_category = new Dialog(this,R.style.Theme_Dialog);
        dialog_of_category.setCancelable(true);
        dialog_of_category.setCanceledOnTouchOutside(false);
        dialog_of_category.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_of_category.setContentView(R.layout.edit_category_item);
        final ImageView imageView = dialog_of_category.findViewById(R.id.image);
        final TextView name = dialog_of_category.findViewById(R.id.name);
        final TextView namear = dialog_of_category.findViewById(R.id.namear);
        Button btn = dialog_of_category.findViewById(R.id.btn_click);
        imageView.setOnClickListener(view -> setDialog());
        if (position != -1){
            name.setText(arrlist.get(position).getName());
            Glide.with(this).load(arrlist.get(position).getImage()).into(imageView);
        }
        btn.setOnClickListener(view -> {
            AddMenuItem model = new AddMenuItem();
            model.setName_en(name.getText().toString());
            model.setName(name.getText().toString());
            model.setName_ar(namear.getText().toString());
            if (position != -1){
                if (imageView != null){
                    model.setImage(file);
                }
                MakeEditRequest(model,position);
            }
            else {
                model.setImage(file);
                model.setRestaurant_id((new SharedPrefDB(getApplicationContext())).getShopData(getApplicationContext()).getMyresturant().getId());
                AddCategory(model);
            }
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

    void MakeEditRequest(final AddMenuItem model, final int position){
        ShowDialogView();
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.MAIN_URL);
        storeServices.MakeEditCategory(arrlist.get(position).getId() , model)
                .enqueue(new Callback<MenuModel>() {
                    @Override
                    public void onResponse(@NonNull Call<MenuModel> call,
                                           @NonNull Response<MenuModel> response) {
                        DismissDialogView();
                        arrlist.set(position,response.body().getMenu());
                        adapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onFailure(@NonNull Call<MenuModel> call, @NonNull Throwable t) {
                        DismissDialogView();
                        Toast.makeText(AllCategories.this,"failed  .. try again",Toast.LENGTH_LONG).show();
                    }
                });
    }
    void AddCategory(final AddMenuItem model){
        ShowDialogView();
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.MAIN_URL);
        storeServices.AddMenu(model)
                .enqueue(new Callback<MenuModel>() {
                    @Override
                    public void onResponse(@NonNull Call<MenuModel> call,
                                           @NonNull Response<MenuModel> response) {
                        DismissDialogView();
                        arrlist.add(response.body().getMenu());
                        adapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onFailure(@NonNull Call<MenuModel> call, @NonNull Throwable t) {
                        DismissDialogView();
                        Toast.makeText(AllCategories.this,"failed  .. try again",Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onlistenToEdit(int position) {
        AddCategoryDialog(position);
    }
    @Override
    public void onlistenToDelete(int position) {
        ShowDialogView();
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.MAIN_URL);
        storeServices.DeleteMenu(arrlist.get(position).getId())
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call,
                                           @NonNull Response<String> response) {
                        DismissDialogView();
                        arrlist.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                        DismissDialogView();
                        arrlist.remove(position);
                        adapter.notifyDataSetChanged();
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

        cancel_container.setOnClickListener(v -> dialog.dismiss());
        gallery_container.setOnClickListener(v -> {
            dialog.dismiss();
            pickFromGallery();
        });
        camera_container.setOnClickListener(v -> {
            dialog.dismiss();
            captureFromCamera();
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
                    ((ImageView)dialog_of_category.findViewById(R.id.image)).setImageURI(selectedImage);
                    break;
                case 33:
                    Glide.with(this).load(url_to_save).into(((ImageView)dialog_of_category.findViewById(R.id.image)));
                    break;
            }

    }
}