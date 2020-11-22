package com.thebest.resturant.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class responsiblesModel {
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("job")
    @Expose
    private String job;
    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
