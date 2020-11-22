package com.thebest.resturant.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class from_to_date {
    @SerializedName("from_date")
    @Expose
    private String from;
    @SerializedName("to_date")
    @Expose
    private String to;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
