package com.thebest.resturant.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderItemsModel {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("price")
    @Expose
    private float price;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("item_id")
    @Expose
    private int item_id;
    @SerializedName("place_id")
    @Expose
    private int place_id;
    @SerializedName("order_id")
    @Expose
    private int order_id;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("variation_id")
    @Expose
    private int variation_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getPlace_id() {
        return place_id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getVariation_id() {
        return variation_id;
    }

    public void setVariation_id(int variation_id) {
        this.variation_id = variation_id;
    }
}
