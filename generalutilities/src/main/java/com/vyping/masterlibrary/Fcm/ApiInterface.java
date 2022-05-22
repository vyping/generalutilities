package com.vyping.masterlibrary.Fcm;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @Headers({"Authorization: key=AAAAQ4qrh3Y:APA91bFzCgVbY-R30uX-MwBIQlfQj-UUjKbpieK_KIBcVD7zS10UJY742ee_gM9ngYD5BK6t_tV2FwM9TRGyvLDBT1fiA8yYRM59ss9iocG4jz-Esumr4h8J2c1sp0IupEBJpQgCtHUB", "Content-Type:application/json"})
    //@POST("fcm/send")
    @POST("messages:send")
    Call<ResponseBody> sendChatNotification(@Body RequestNotificaton requestNotificaton);
}