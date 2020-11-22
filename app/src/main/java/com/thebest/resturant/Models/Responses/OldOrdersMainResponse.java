package com.thebest.resturant.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OldOrdersMainResponse {
    @SerializedName("OldOrders")
    @Expose
    private ArrayList<CurrnetTrip> OldOrders;
    @SerializedName("NewOrders")
    @Expose
    private ArrayList<CurrnetTrip> NewOrders;

    public ArrayList<CurrnetTrip> getNewOrders() {
        return NewOrders;
    }

    public void setNewOrders(ArrayList<CurrnetTrip> newOrders) {
        NewOrders = newOrders;
    }

    public ArrayList<CurrnetTrip> getOldOrders() {
        return OldOrders;
    }

    public void setOldOrders(ArrayList<CurrnetTrip> oldOrders) {
        OldOrders = oldOrders;
    }
}
