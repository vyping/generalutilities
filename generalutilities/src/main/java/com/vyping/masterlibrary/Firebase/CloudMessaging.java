package com.vyping.masterlibrary.Firebase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CloudMessaging {

    public static String urlSendFcm, serverKey;
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


    private void setDataServer(Context context, int defaults) {

        new RemoteConfig().CloudMessaging(context, defaults, new RemoteConfig.MessagingListener() {

            @Override
            public void ServerData(String urlServer, String keyServer) {

                urlSendFcm = urlServer;
                serverKey = keyServer;
            }

            @Override
            public void errorOnReceive() {
            }
        });
    }

    public static void SendAssingService(@NonNull String destinToken, String Indications, String Hour, String Date, String Service) {

        if (!destinToken.equals("")) {


            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... items) {

                    try {

                        JSONObject dataJson = new JSONObject();
                        dataJson.put("Service", Service);
                        dataJson.put("Indications", Indications);
                        dataJson.put("Date", Date);
                        dataJson.put("Hour", Hour);

                        JSONObject json = new JSONObject();
                        json.put("to", destinToken);
                        json.put("data", dataJson);

                        RequestBody body = RequestBody.create(JSON, json.toString());
                        Request request = new Request.Builder()
                                .header("Authorization", "key=AAAAQ4qrh3Y:APA91bFzCgVbY-R30uX-MwBIQlfQj-UUjKbpieK_KIBcVD7zS10UJY742ee_gM9ngYD5BK6t_tV2FwM9TRGyvLDBT1fiA8yYRM59ss9iocG4jz-Esumr4h8J2c1sp0IupEBJpQgCtHUB")
                                //.header("Authorization", "key=" + serverKey)
                                .url("https://fcm.googleapis.com/fcm/send")
                                //.url(urlSendFcm)
                                .post(body)
                                .build();

                        OkHttpClient client = new OkHttpClient();
                        Response response = client.newCall(request).execute();

                    } catch (Exception e) {

                        Log.i("Desarrollo", "e: " + e);
                    }

                    return null;
                }

            }.execute();
        }
    }

    public static void SendResponseService(String destinToken, int icon, String title, String body, String subTitle, int sound, long Count) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... items) {

                try {

                    OkHttpClient client = new OkHttpClient();
                    JSONObject json = new JSONObject();
                    JSONObject notJson = new JSONObject();
                    JSONObject datJson = new JSONObject();
                    datJson.put("title", title);
                    datJson.put("body", body);
                    datJson.put("subtitle", subTitle);
                    datJson.put("object_id", "0");

                    String service = String.valueOf(Count);

                    datJson.put("service", service);
                    datJson.put("type", "ResponseService");
                    json.put("data", datJson);
                    notJson.put("title", title);
                    notJson.put("body", body);
                    notJson.put("subtitle", subTitle);
                    notJson.put("object_id", "0");
                    notJson.put("tag", "ResponseService");
                    notJson.put("icon", icon);
                    notJson.put("sound", sound);
                    json.put("click_action", ".Admin.Routes.Adm_Rts_Activity");
                    json.put("notification", notJson);
                    json.put("priority", "high");
                    json.put("to", destinToken);

                    RequestBody requestBody = RequestBody.create(JSON, json.toString());
                    Request request = new Request.Builder()
                            .header("Authorization", "key=AAAAQ4qrh3Y:APA91bFzCgVbY-R30uX-MwBIQlfQj-UUjKbpieK_KIBcVD7zS10UJY742ee_gM9ngYD5BK6t_tV2FwM9TRGyvLDBT1fiA8yYRM59ss9iocG4jz-Esumr4h8J2c1sp0IupEBJpQgCtHUB")
                            .url("https://fcm.googleapis.com/fcm/send")
                            .post(requestBody)
                            .build();
                    client.newCall(request).execute();

                } catch (Exception ignored) {
                }

                return null;
            }

        }.execute();
    }
}
