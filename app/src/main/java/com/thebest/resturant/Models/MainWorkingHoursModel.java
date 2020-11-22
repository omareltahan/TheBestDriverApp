package com.thebest.resturant.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MainWorkingHoursModel {
    private ArrayList<WorkingHoursModel> workingHours = new ArrayList<>();

    public ArrayList<WorkingHoursModel> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(ArrayList<WorkingHoursModel> workingHours) {
        this.workingHours = workingHours;
    }
}
