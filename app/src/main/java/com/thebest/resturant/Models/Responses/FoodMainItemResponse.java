package com.thebest.resturant.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FoodMainItemResponse {
    @SerializedName("items")
    @Expose
    private ArrayList<FoodMainItem> items = new ArrayList<>();

    public ArrayList<FoodMainItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<FoodMainItem> items) {
        this.items = items;
    }

    @SerializedName("MainCategories")
    @Expose
    private ArrayList<FoodMainItem> MainCategories = new ArrayList<>();

    public ArrayList<FoodMainItem> getMainCategories() {
        return MainCategories;
    }

    public void setMainCategories(ArrayList<FoodMainItem> mainCategories) {
        MainCategories = mainCategories;
    }
}
