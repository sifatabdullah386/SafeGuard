package com.example.safeguard;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Notification extends Application {
    public static final  String CHANNEL_ID="channel";
    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }
    private  void createNotificationChannels() {

            NotificationChannel channel = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                channel = new NotificationChannel(
                        CHANNEL_ID,
                        "channel",
                        NotificationManager.IMPORTANCE_HIGH
                );
            }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel.setDescription("Notification has been send");
        }
        //channel.setSound();
        //channel.setLockscreenVisibility();
        NotificationManager manager = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            manager = getSystemService(NotificationManager.class);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(channel);
        }

    }
}
