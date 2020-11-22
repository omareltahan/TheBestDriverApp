package com.thebest.resturant.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyMenusMainResponse {
    @SerializedName("MyMenus")
    @Expose
    private ArrayList<MyMenusItemModel> MyMenus;

    public ArrayList<MyMenusItemModel> getMyMenus() {
        return MyMenus;
    }

    public void setMyMenus(ArrayList<MyMenusItemModel> myMenus) {
        MyMenus = myMenus;
    }
}
