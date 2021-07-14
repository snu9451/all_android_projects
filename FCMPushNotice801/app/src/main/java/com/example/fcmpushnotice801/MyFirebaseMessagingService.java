package com.example.fcmpushnotice801;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
    }

    public void onMessageReceived(RemoteMessage message){
        Log.i("MyFirebaseMessaging", "message:"+message);
        super.onMessageReceived(message);
    }
}
