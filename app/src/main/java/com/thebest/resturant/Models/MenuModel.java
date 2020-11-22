package com.thebest.resturant.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.thebest.resturant.Models.Requests.AddMenuItem;
import com.thebest.resturant.Models.Responses.MyMenusItemModel;

public class MenuModel {
    @SerializedName("Menu")
    @Expose
    private MyMenusItemModel Menu;

    public MyMenusItemModel getMenu() {
        return Menu;
    }

    public void setMenu(MyMenusItemModel menu) {
        Menu = menu;
    }
}
