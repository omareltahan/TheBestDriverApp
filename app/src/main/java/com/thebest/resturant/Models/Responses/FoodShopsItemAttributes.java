package com.thebest.resturant.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FoodShopsItemAttributes implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("name_ar")
    @Expose
    private String name_ar;
    @SerializedName("name_en")
    @Expose
    private String name_en;

    public String getName_en() {
        return name_en;
    }

    public String getName_ar() {
        return name_ar;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public void setName_ar(String name_ar) {
        this.name_ar = name_ar;
    }

    @SerializedName("item_id")
    @Expose
    private int item_id;
    @SerializedName("has_image")
    @Expose
    private String image;


    public String getPrice() {
        return price;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getId() {
        return id;
    }


    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
