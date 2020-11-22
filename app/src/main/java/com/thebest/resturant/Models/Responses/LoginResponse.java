package com.thebest.resturant.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.thebest.resturant.Models.WorkingHoursModel;
import com.thebest.resturant.Models.responsiblesModel;

import java.io.File;
import java.util.ArrayList;

public class LoginResponse {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_en")
    @Expose
    private String name_en;
    @SerializedName("has_image")
    @Expose
    private String image;
    @SerializedName("description")
    @Expose
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @SerializedName("description_en")
    @Expose
    private String description_en;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("address_en")
    @Expose
    private String address_en;
    @SerializedName("category_id")
    @Expose
    private String category_id;


    @SerializedName("delivery_price")
    @Expose
    private int delivery_price;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lng")
    @Expose
    private double lng;
    @SerializedName("type_id")
    @Expose
    private int type_id;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("government")
    @Expose
    private String government;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("place_owner_name")
    @Expose
    private String place_owner_name;
    @SerializedName("ownerimage")
    @Expose
    private String ownerimage;
    @SerializedName("imgcert")
    @Expose
    private String imgcert;
    @SerializedName("place_email")
    @Expose
    private String place_email;
    @SerializedName("place_phone")
    @Expose
    private String place_phone;
    @SerializedName("order_limit")
    @Expose
    private int order_limit;
    @SerializedName("banking_id")
    @Expose
    private String banking_id;
//    @SerializedName("branches")
//    @Expose
//    private ArrayList<LoginResponse> branches;
    @SerializedName("working_hours")
    @Expose
    private Object working_hours;
    @SerializedName("responsibles")
    @Expose
    private Object responsibles;
    @SerializedName("signatureimage")
    @Expose
    private String signatureimage;
    @SerializedName("menues_categories")
    @Expose
    private ArrayList<MyMenusItemModel> menues_categories;

    public ArrayList<MyMenusItemModel> getMenues_categories() {
        return menues_categories;
    }

    public void setMenues_categories(ArrayList<MyMenusItemModel> menues_categories) {
        this.menues_categories = menues_categories;
    }

    public String getSignatureimage() {
        return signatureimage;
    }

    public void setSignatureimage(String signatureimage) {
        this.signatureimage = signatureimage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress_en() {
        return address_en;
    }

    public void setAddress_en(String address_en) {
        this.address_en = address_en;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public int getDelivery_price() {
        return delivery_price;
    }

    public void setDelivery_price(int delivery_price) {
        this.delivery_price = delivery_price;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGovernment() {
        return government;
    }

    public void setGovernment(String government) {
        this.government = government;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPlace_owner_name() {
        return place_owner_name;
    }

    public void setPlace_owner_name(String place_owner_name) {
        this.place_owner_name = place_owner_name;
    }

    public String getOwnerimage() {
        return ownerimage;
    }

    public void setOwnerimage(String ownerimage) {
        this.ownerimage = ownerimage;
    }

    public String getImgcert() {
        return imgcert;
    }

    public void setImgcert(String imgcert) {
        this.imgcert = imgcert;
    }

    public String getPlace_email() {
        return place_email;
    }

    public void setPlace_email(String place_email) {
        this.place_email = place_email;
    }

    public String getPlace_phone() {
        return place_phone;
    }

    public void setPlace_phone(String place_phone) {
        this.place_phone = place_phone;
    }

    public int getOrder_limit() {
        return order_limit;
    }

    public void setOrder_limit(int order_limit) {
        this.order_limit = order_limit;
    }

    public String getBanking_id() {
        return banking_id;
    }

    public void setBanking_id(String banking_id) {
        this.banking_id = banking_id;
    }

//    public ArrayList<LoginResponse> getBranches() {
//        return branches;
//    }
//
//    public void setBranches(ArrayList<LoginResponse> branches) {
//        this.branches = branches;
//    }

    public Object getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(Object working_hours) {
        this.working_hours = working_hours;
    }

    public Object getResponsibles() {
        return responsibles;
    }

    public void setResponsibles(Object responsibles) {
        this.responsibles = responsibles;
    }
}
