package com.thebest.resturant.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FoodShopsCategoriesProductsResponse {

    @SerializedName("RestaurantMenu")
    @Expose
    private ArrayList<FoodShopsCategoriesProductsitem> RestaurantMenu = new ArrayList<>();

    public ArrayList<FoodShopsCategoriesProductsitem> getRestaurantMenu() {
        return RestaurantMenu;
    }

    public void setRestaurantMenu(ArrayList<FoodShopsCategoriesProductsitem> restaurantMenu) {
        RestaurantMenu = restaurantMenu;
    }
}
