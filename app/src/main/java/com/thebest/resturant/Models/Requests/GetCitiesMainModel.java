package com.thebest.resturant.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.ArrayList;

public class GetCitiesMainModel {
    @SerializedName("Cities")
    @Expose
    private ArrayList<GetCitiesItemModel> Cities = new ArrayList<>();

    public ArrayList<GetCitiesItemModel> getCities() {
        return Cities;
    }

    public void setCities(ArrayList<GetCitiesItemModel> cities) {
        Cities = cities;
    }
}
