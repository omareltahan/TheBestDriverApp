package com.thebest.resturant.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

public class AddMenuItem {
    @SerializedName("name_ar")
    @Expose
    private String name_ar;
    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("image")
    @Expose
    private File image;

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    @SerializedName("name_en")
    @Expose
    private String name_en;
    @SerializedName("restaurant_id")
    @Expose
    private int restaurant_id;

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public String getName_ar() {
        return name_ar;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_ar(String name_ar) {
        this.name_ar = name_ar;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
}
