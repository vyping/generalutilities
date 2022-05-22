package com.vyping.masterlibrary.Fcm;

import com.google.gson.annotations.SerializedName;

public class RequestNotificaton {

    @SerializedName("token") //  "to" changed to token
    private String token;

    @SerializedName("notification")
    private SendNotificationModel sendNotificationModel;

    public SendNotificationModel getSendNotificationModel() {
        return sendNotificationModel;
    }

    public void setSendNotificationModel(SendNotificationModel sendNotificationModel) {
        this.sendNotificationModel = sendNotificationModel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
