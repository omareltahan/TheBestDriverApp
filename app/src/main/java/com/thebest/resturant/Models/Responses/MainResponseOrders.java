package com.thebest.resturant.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MainResponseOrders {
    @SerializedName("MyOrders")
    @Expose
    private ArrayList<CurrnetTrip> MyOrders;

    public ArrayList<CurrnetTrip>  getMyOrders() {
        return MyOrders;
    }

    public void setMyOrders(ArrayList<CurrnetTrip>  myOrders) {
        MyOrders = myOrders;
    }


    @SerializedName("MyOrdersCount")
    @Expose
    private int MyOrdersCount;
    @SerializedName("MyOrdersDone")
    @Expose
    private int MyOrdersDone;
    @SerializedName("MyOrdersCanceled")
    @Expose
    private int MyOrdersCanceled;
    @SerializedName("MyOrdersMoney")
    @Expose
    private int MyOrdersMoney;

    public int getMyOrdersCanceled() {
        return MyOrdersCanceled;
    }

    public int getMyOrdersCount() {
        return MyOrdersCount;
    }

    public int getMyOrdersDone() {
        return MyOrdersDone;
    }

    public int getMyOrdersMoney() {
        return MyOrdersMoney;
    }

    public void setMyOrdersCanceled(int myOrdersCanceled) {
        MyOrdersCanceled = myOrdersCanceled;
    }

    public void setMyOrdersCount(int myOrdersCount) {
        MyOrdersCount = myOrdersCount;
    }

    public void setMyOrdersDone(int myOrdersDone) {
        MyOrdersDone = myOrdersDone;
    }

    public void setMyOrdersMoney(int myOrdersMoney) {
        MyOrdersMoney = myOrdersMoney;
    }
}