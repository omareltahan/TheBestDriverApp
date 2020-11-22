package com.thebest.resturant.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetDistrictsMainModel {
    @SerializedName("Districts")
    @Expose
    private ArrayList<GetCitiesItemModel> Districts = new ArrayList<>();
    public ArrayList<GetCitiesItemModel> getDistricts() {
        return Districts;
    }
    public void setDistricts(ArrayList<GetCitiesItemModel> districts) {
        Districts = districts;
    }
}
