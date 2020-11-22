package com.thebest.resturant.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CurrnetTrip {
    @SerializedName("image")
    @Expose
    private String image = "";
    private int time_date = "";

    public int getTime_date() {
        return time_date;
    }

    public void setTime_date(int time_date) {
        this.time_date = time_date;
    }

    @SerializedName("created_at")
    @Expose
    private String created_at = "";

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("order_items")
    @Expose
    private ArrayList<OrderItemsModel> order_items;

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("client_id")
    @Expose
    private int client_id;
    @SerializedName("driver_id")
    @Expose
    private int driver_id;
    @SerializedName("from_lat")
    @Expose
    private double from_lat;
    @SerializedName("from_lng")
    @Expose
    private double from_lng;
    @SerializedName("to_lat")
    @Expose
    private double to_lat;
    @SerializedName("to_lng")
    @Expose
    private double to_lng;

    @SerializedName("payment_method")
    @Expose
    private String payment_method;
    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("address_from")
    @Expose
    private String address_from;
    @SerializedName("address_to")
    @Expose
    private String address_to;
    @SerializedName("driver_comment")
    @Expose
    private String driver_comment;
    @SerializedName("ride_type")
    @Expose
    private int ride_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public double getFrom_lat() {
        return from_lat;
    }

    public void setFrom_lat(double from_lat) {
        this.from_lat = from_lat;
    }

    public double getFrom_lng() {
        return from_lng;
    }

    public void setFrom_lng(double from_lng) {
        this.from_lng = from_lng;
    }

    public double getTo_lat() {
        return to_lat;
    }

    public void setTo_lat(double to_lat) {
        this.to_lat = to_lat;
    }

    public double getTo_lng() {
        return to_lng;
    }

    public void setTo_lng(double to_lng) {
        this.to_lng = to_lng;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public int getTotal() {
        return total;
    }

    public ArrayList<OrderItemsModel> getOrder_items() {
        return order_items;
    }

    public String getUsername() {
        return username;
    }

    public void setOrder_items(ArrayList<OrderItemsModel> order_items) {
        this.order_items = order_items;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress_from() {
        return address_from;
    }

    public void setAddress_from(String address_from) {
        this.address_from = address_from;
    }

    public String getAddress_to() {
        return address_to;
    }

    public void setAddress_to(String address_to) {
        this.address_to = address_to;
    }

    public String getDriver_comment() {
        return driver_comment;
    }

    public void setDriver_comment(String driver_comment) {
        this.driver_comment = driver_comment;
    }

    public int getRide_type() {
        return ride_type;
    }

    public void setRide_type(int ride_type) {
        this.ride_type = ride_type;
    }
}
