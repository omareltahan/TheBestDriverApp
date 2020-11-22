package com.thebest.resturant.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.thebest.resturant.Models.Requests.RequestLogin;
import com.thebest.resturant.Models.Responses.LoginResponse;
import com.thebest.resturant.Models.Responses.MainLoginResponse;

public class SharedPrefDB {
    Context context;
    public String GetStrFromSharedPref(String key){
        return context.getSharedPreferences(Constant.SharedPrefModeName,Context.MODE_PRIVATE).getString(key,"");
    }
    public int GetIntFromSharedPref(String key){
        return context.getSharedPreferences(Constant.SharedPrefModeName,Context.MODE_PRIVATE).getInt(key,0);
    }
    public boolean GetBoolFromSharedPref(String key){
        return context.getSharedPreferences(Constant.SharedPrefModeName,Context.MODE_PRIVATE).getBoolean(key,false);
    }
    public float GetFloatFromSharedPref(String key){
        return context.getSharedPreferences(Constant.SharedPrefModeName,Context.MODE_PRIVATE).getFloat(key,0);
    }

    public void cacheShopData(Context context, MainLoginResponse loginResponse) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Log.e("cashedone",new Gson().toJson(loginResponse));
        defaultSharedPreferences.edit().putString("shop_data", new Gson().toJson(loginResponse)).apply();
    }

    public MainLoginResponse getShopData(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String user_data = defaultSharedPreferences.getString("shop_data", null);
        if (user_data != null) {
            return new Gson().fromJson(user_data, MainLoginResponse.class);
        }
        Log.e("getShopData","null null");
        return null;
    }


    public void SetStrFromSharedPref(String key,String value){
        context.getSharedPreferences(Constant.SharedPrefModeName,Context.MODE_PRIVATE).edit().putString(key,value).apply();
    }
    public void SetIntFromSharedPref(String key,int value){
        context.getSharedPreferences(Constant.SharedPrefModeName,Context.MODE_PRIVATE).edit().putInt(key,value).apply();
    }
    public void SetBoolFromSharedPref(String key,boolean value){
        context.getSharedPreferences(Constant.SharedPrefModeName,Context.MODE_PRIVATE).edit().putBoolean(key,value).apply();
    }
    public void SetFloatFromSharedPref(String key,float value){
        context.getSharedPreferences(Constant.SharedPrefModeName, Context.MODE_PRIVATE).edit().putFloat(key, value).apply();
    }
    public SharedPrefDB(Context context){
        this.context = context;
    }
}
