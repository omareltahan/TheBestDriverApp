package com.thebest.resturant.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.thebest.resturant.Models.WorkingHoursModel;
import com.thebest.resturant.Models.responsiblesModel;

import java.io.File;
import java.util.ArrayList;

public class RequestRegister {
    @SerializedName("name_ar")
    @Expose
    private String name_ar;
    @SerializedName("name_en")
    @Expose
    private String name_en;
    @SerializedName("imagere")
    @Expose
    private File imagere;
    @SerializedName("description")
    @Expose
    private String description;
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
    private String delivery_price;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("type_id")
    @Expose
    private String type_id;
    @SerializedName("government")
    @Expose
    private String government;
    @SerializedName("district")
    @Expose
    private String district;



    @SerializedName("place_owner_name")
    @Expose
    private String place_owner_name;
    @SerializedName("imgcert")
    @Expose
    private File imgcert;
    @SerializedName("cert")
    @Expose
    private String cert;
    @SerializedName("signatur")
    @Expose
    private String signatur;

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }

    public void setSignatur(String signatur) {
        this.signatur = signatur;
    }

    public String getSignatur() {
        return signatur;
    }

    @SerializedName("place_email")
    @Expose
    private String place_email;
    @SerializedName("signatureimage")
    @Expose
    private File signatureimage;
    @SerializedName("place_phone")
    @Expose
    private String place_phone;
    @SerializedName("order_limit")
    @Expose
    private String order_limit;
    @SerializedName("working_hours")
    @Expose
    private ArrayList<WorkingHoursModel> working_hours;
    @SerializedName("responsibles")
    @Expose
    private ArrayList<responsiblesModel> responsibles = new ArrayList<>();



    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("password")
    @Expose
    private String password;


    @SerializedName("fcm_token")
    @Expose
    private String fcm_token;


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

    public File getImagere() {
        return imagere;
    }

    public void setImagere(File imagere) {
        this.imagere = imagere;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getDelivery_price() {
        return delivery_price;
    }

    public void setDelivery_price(String delivery_price) {
        this.delivery_price = delivery_price;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
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

    public File getImgcert() {
        return imgcert;
    }

    public void setImgcert(File imgcert) {
        this.imgcert = imgcert;
    }

    public String getPlace_email() {
        return place_email;
    }

    public void setPlace_email(String place_email) {
        this.place_email = place_email;
    }

    public File getSignatureimage() {
        return signatureimage;
    }

    public void setSignatureimage(File signatureimage) {
        this.signatureimage = signatureimage;
    }

    public String getPlace_phone() {
        return place_phone;
    }

    public void setPlace_phone(String place_phone) {
        this.place_phone = place_phone;
    }

    public String getOrder_limit() {
        return order_limit;
    }

    public void setOrder_limit(String order_limit) {
        this.order_limit = order_limit;
    }

    public ArrayList<responsiblesModel> getResponsibles() {
        return responsibles;
    }

    public ArrayList<WorkingHoursModel> getWorking_hours() {
        return working_hours;
    }

    public void setResponsibles(ArrayList<responsiblesModel> responsibles) {
        this.responsibles = responsibles;
    }

    public void setWorking_hours(ArrayList<WorkingHoursModel> working_hours) {
        this.working_hours = working_hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }
}
