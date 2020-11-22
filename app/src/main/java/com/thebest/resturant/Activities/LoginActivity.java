package com.thebest.resturant.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.thebest.resturant.Models.Requests.RequestLogin;
import com.thebest.resturant.Models.Responses.MainLoginResponse;
import com.thebest.resturant.Networking.ServiceGenerator;
import com.thebest.resturant.Networking.StoreAPIInterface;
import com.thebest.resturant.R;
import com.thebest.resturant.Utilities.Constant;
import com.thebest.resturant.Utilities.MyFirebaseInstanceIDService;
import com.thebest.resturant.Utilities.SharedPrefDB;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    TextView signup;
    Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        signin = findViewById(R.id.login);
        signup.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, SignUpActivity.class)));
        signin.setOnClickListener(view -> FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( LoginActivity.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                MakeRequest(newToken);
                MyFirebaseInstanceIDService service = new MyFirebaseInstanceIDService();
                service.onNewToken(newToken);
            }
        }));
    }
    void MakeRequest(String token){

        ShowDialogView();
        RequestLogin requestLogin = new RequestLogin();
        requestLogin.setEmail(email.getText().toString());
        requestLogin.setPassword(password.getText().toString());
        requestLogin.setFcm_token(token);
        StoreAPIInterface storeServices = ServiceGenerator.createService(
                StoreAPIInterface.class, Constant.AUTH_URL);
        storeServices.MakeLogin(requestLogin)
                .enqueue(new Callback<MainLoginResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MainLoginResponse> call,
                                           @NonNull Response<MainLoginResponse> response) {
                        DismissDialogView();
                        if (response.body() == null || response.body().getMyresturant() == null){
                            Toast.makeText(LoginActivity.this,"Enter Correct Email and Password",Toast.LENGTH_LONG).show();
                        }
                        else {
                            (new SharedPrefDB(getApplicationContext())).SetBoolFromSharedPref(Constant.SharedPrefIsLogged, true);
                            SharedPrefDB sharedPrefDB = new SharedPrefDB(LoginActivity.this);
                            sharedPrefDB.cacheShopData(LoginActivity.this, response.body());
                            startActivity(new Intent(LoginActivity.this, AllCategories.class));
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MainLoginResponse> call, @NonNull Throwable t) {
                        DismissDialogView();
                        Log.e("errorThrowable",t.getMessage());
                        Toast.makeText(LoginActivity.this,"failed  .. try again",Toast.LENGTH_LONG).show();
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

}