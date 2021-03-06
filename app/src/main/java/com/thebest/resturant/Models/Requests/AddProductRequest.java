package com.thebest.resturant.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.thebest.resturant.Models.Responses.FoodShopsItemAttributes;

import java.io.File;
import java.util.ArrayList;

public class AddProductRequest {
    @SerializedName("name_ar")
    @Expose
    private String name_ar;
    @SerializedName("name_en")
    @Expose
    private String name_en;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("image")
    @Expose
    private File image;
    @SerializedName("description_ar")
    @Expose
    private String description_ar;
    @SerializedName("description_en")
    @Expose
    private String description_en;

    @SerializedName("restaurant_id")
    @Expose
    private String restaurant_id;
    @SerializedName("menu_category_id")
    @Expose
    private String menu_category_id;

    @SerializedName("cat_id")
    @Expose
    private String cat_id;
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

    public String getName_ar() {
        return name_ar;
    }

    public void setName_ar(String name_ar) {
        this.name_ar = name_ar;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getDescription_ar() {
        return description_ar;
    }

    public void setDescription_ar(String description_ar) {
        this.description_ar = description_ar;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getMenu_category_id() {
        return menu_category_id;
    }

    public void setMenu_category_id(String menu_category_id) {
        this.menu_category_id = menu_category_id;
    }

    public Object getItemattributes() {
        return itemattributes;
    }

    public void setItemattributes(Object itemattributes) {
        this.itemattributes = itemattributes;
    }

    public Object getItemattributes_two() {
        return itemattributes_two;
    }

    public void setItemattributes_two(Object itemattributes_two) {
        this.itemattributes_two = itemattributes_two;
    }

    public Object getItemattributes_third() {
        return itemattributes_third;
    }

    public void setItemattributes_third(Object itemattributes_third) {
        this.itemattributes_third = itemattributes_third;
    }

    public Object getAdditionalAttributes() {
        return AdditionalAttributes;
    }

    public void setAdditionalAttributes(Object additionalAttributes) {
        AdditionalAttributes = additionalAttributes;
    }

    public String getAttribute_title() {
        return attribute_title;
    }

    public void setAttribute_title(String attribute_title) {
        this.attribute_title = attribute_title;
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

    public String getAttribute_title_en() {
        return attribute_title_en;
    }

    public void setAttribute_title_en(String attribute_title_en) {
        this.attribute_title_en = attribute_title_en;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }
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
}
