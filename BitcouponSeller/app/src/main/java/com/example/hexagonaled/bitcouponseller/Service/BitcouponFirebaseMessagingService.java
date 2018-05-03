package com.example.hexagonaled.bitcouponseller.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.hexagonaled.bitcouponseller.Common.Config;
import com.example.hexagonaled.bitcouponseller.MainActivity;
import com.example.hexagonaled.bitcouponseller.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

import static android.content.ContentValues.TAG;

/**
 * Created by hexagonaled on 02/05/2018.
 */

public class BitcouponFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        //super.onMessageReceived(remoteMessage);
        Log.d("FMS","onMessageReceived");
        handleMessage(remoteMessage.getData().get(Config.STR_KEY));
    }

    private void handleMessage(String message){
        //Log.d(TAG, "Title:" + title + ", Message:" + message);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Config.STR_KEY, message);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "com.example.hexagonaled.bitcouponseller");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"com.example.hexagonaled.bitcouponseller.ANDROID");
        builder.setAutoCancel(true)
                //.setWhen(System.currentTimeMillis())
                //.setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("BITCOUPON DEV")
                .setContentText(message)
                .setContentIntent(contentIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Log.d("FMS","handlemessage");
        notificationManager.notify(new Random().nextInt(), builder.build());
    }
    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        Log.d("ondelete",null);
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
        Log.d("onmessagesent",null);
    }

    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
        Log.d("onsenderror", null);
    }
}
