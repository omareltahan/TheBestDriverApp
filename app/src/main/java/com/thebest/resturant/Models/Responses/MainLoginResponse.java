package com.thebest.resturant.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainLoginResponse {
    @SerializedName("Myresturant")
    @Expose
    private LoginResponse Myresturant;
    @SerializedName("accessToken")
    @Expose
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public LoginResponse getMyresturant() {
        return Myresturant;
    }

    public void setMyresturant(LoginResponse myresturant) {
        Myresturant = myresturant;
    }
}
