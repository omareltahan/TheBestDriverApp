package com.thebest.resturant.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class FoodShopsCategoriesProductsitem implements Serializable {

    @SerializedName("attribute_body")
    @Expose
    private Object itemattributes = new ArrayList<>();
    @SerializedName("attribute_body_two")
    @Expose
    private Object itemattributes_two = new ArrayList<>();
    @SerializedName("attribute_body_three")
    @Expose
    private Object itemattributes_third = new ArrayList<>();
    @SerializedName("AdditionalItem")
    @Expose
    private Object AdditionalAttributes = new ArrayList<>();
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("menu_category_id")
    @Expose
    private int menu_category_id;
    @SerializedName("restaurant_id")
    @Expose
    private int restaurant_id;
    @SerializedName("attribute_title")
    @Expose
    private String attribute_title;
    @SerializedName("attribute_title_en")
    @Expose
    private String attribute_title_en;
    @SerializedName("attribute_title_two")
    @Expose
    private String attribute_title_two;
    @SerializedName("attribute_title_en_two")
    @Expose
    private String attribute_title_en_two;
    @SerializedName("attribute_title_three")
    @Expose
    private String attribute_title_third;
    @SerializedName("attribute_title_en_three")
    @Expose
    private String attribute_title_en_third;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_en")
    @Expose
    private String name_en;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("description_en")
    @Expose
    private String description_en;

    public String getName_en() {
        return name_en;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    @SerializedName("has_image")
    @Expose
    private String image;

    public String getDescription() {
        return description;
    }

    public Object getItemattributes() {
        return itemattributes;
    }

    public int getMenu_category_id() {
        return menu_category_id;
    }

    public String getPrice() {
        return price.replace(",","");
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setMenu_category_id(int menu_category_id) {
        this.menu_category_id = menu_category_id;
    }

    public void setItemattributes(Object itemattributes) {
        this.itemattributes = itemattributes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAttribute_title() {
        return attribute_title;
    }

    public void setAttribute_title(String attribute_title) {
        this.attribute_title = attribute_title;
    }

    public Object getAdditionalAttributes() {
        return AdditionalAttributes;
    }

    public Object getItemattributes_third() {
        return itemattributes_third;
    }

    public Object getItemattributes_two() {
        return itemattributes_two;
    }

    public void setItemattributes_two(Object itemattributes_two) {
        this.itemattributes_two = itemattributes_two;
    }

    public void setItemattributes_third(Object itemattributes_third) {
        this.itemattributes_third = itemattributes_third;
    }

    public void setAdditionalAttributes(Object additionalAttributes) {
        AdditionalAttributes = additionalAttributes;
    }

    public String getAttribute_title_en() {
        return attribute_title_en;
    }

    public void setAttribute_title_en(String attribute_title_en) {
        this.attribute_title_en = attribute_title_en;
    }

    public String getAttribute_title_two() {
        return attribute_title_two;
    }

    public void setAttribute_title_two(String attribute_title_two) {
        this.attribute_title_two = attribute_title_two;
    }

    public String getAttribute_title_en_two() {
        return attribute_title_en_two;
    }

    public void setAttribute_title_en_two(String attribute_title_en_two) {
        this.attribute_title_en_two = attribute_title_en_two;
    }

    public String getAttribute_title_third() {
        return attribute_title_third;
    }

    public void setAttribute_title_third(String attribute_title_third) {
        this.attribute_title_third = attribute_title_third;
    }

    public String getAttribute_title_en_third() {
        return attribute_title_en_third;
    }

    public void setAttribute_title_en_third(String attribute_title_en_third) {
        this.attribute_title_en_third = attribute_title_en_third;
    }
}
