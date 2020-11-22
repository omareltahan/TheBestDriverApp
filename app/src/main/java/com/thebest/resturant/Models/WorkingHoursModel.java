package com.thebest.resturant.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkingHoursModel {
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("start2")
    @Expose
    private String start2;
    @SerializedName("end2")
    @Expose
    private String end2;
    @SerializedName("end")
    @Expose
    private String end;

    public String getEnd2() {
        return end2;
    }

    public String getStart2() {
        return start2;
    }

    public void setEnd2(String end2) {
        this.end2 = end2;
    }

    public void setStart2(String start2) {
        this.start2 = start2;
    }

    public String getDay() {
        return day;
    }

    public String getEnd() {
        return end;
    }

    public String getStart() {
        return start;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
