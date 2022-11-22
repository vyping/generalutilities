package com.vyping.masterlibrary.Firebase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.vyping.masterlibrary.Common.LogCat;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CloudMessaging {

    public static String urlSendFcm = "https://fcm.googleapis.com/fcm/send";
    public static String serverKey = "AAAAxM51bAo:APA91bF6hl-rvFvXulIBVLRz9XnpQF4_SBe39nLuN5RkLAujQMBC3SIPaTFbM1KPmEjsbLPxj8bllANwORHoM6RLUg14qY8wSH9ZsQ0JeE2OQpigly4C_EEM1nWMYkJNnqj5H8LvNUlN";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    public void requestCloudMessageToken(MyRealtime realtime, String tag) {

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {

            @Override
            public void onComplete(@NonNull Task<String> task) {

                if (!task.isSuccessful()) {

                    return;
                }

                String token = task.getResult();

                realtime.child(tag).setValue(token);
            }

            private void DummyVoid() {};
        });
    }

    public void resetCloudMessageToken(String instance, String path) {

        new MyRealtime(instance).child(path).setValue("");
    }

    public static void SendAssingService(@NonNull String serverKey, String destinToken, String title, String body) {

        if (!destinToken.equals("")) {

            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... items) {

                    try {

                        JSONObject dataJson = new JSONObject();
                        dataJson.put("title", title);
                        dataJson.put("body", body);
                        //  dataJson.put("sound", "notification.mp3");

                        JSONObject json = new JSONObject();
                        json.put("to", destinToken);
                        json.put("notification", dataJson);
                        //  dataJson.put("message", "Message");

                        RequestBody body = RequestBody.create(JSON, json.toString());

                        Request request = new Request.Builder()
                                .header("Authorization", "key=" + serverKey)
                                .header("Content-Type", "application/json")
                                .url("https://fcm.googleapis.com/fcm/send")
                                .post(body)
                                .build();

                        OkHttpClient client = new OkHttpClient();
                        Response response = client.newCall(request).execute();

                    } catch (Exception e) {

                        new LogCat("e: " + e.getMessage());
                    }

                    return null;

                }

            }.execute();
        }
    }
}
