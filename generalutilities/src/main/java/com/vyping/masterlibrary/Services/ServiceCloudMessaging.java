package com.vyping.masterlibrary.Services;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

// TODO --- Sección encargada de recibir los mensajes de Firebase Cloud Messaging

public class ServiceCloudMessaging extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getNotification() != null) {


        }
    }

    @Override
    public void onNewToken(@NonNull String s) {

        super.onNewToken(s);
    }
}
