package com.thebest.resturant.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

public class responsabilitiesRegister {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("job")
    @Expose
    private String job;
    @SerializedName("phone")
    @Expose
    private String phone;
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getJob() {
        return job;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
