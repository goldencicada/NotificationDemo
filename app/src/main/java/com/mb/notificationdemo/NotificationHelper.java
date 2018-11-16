package com.mb.notificationdemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;

/**
 * 通知栏 创建和管理帮助类
 */
public class NotificationHelper extends ContextWrapper {

    private NotificationManager manager;
    public static final String DEFAULT_CHANNEL = "default";
    public static final String SECOND_CHANNEL = "second";

    public NotificationHelper(Context base) {
        super(base);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(DEFAULT_CHANNEL,
                    "default_channel" ,NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            getManager().createNotificationChannel(notificationChannel);

            NotificationChannel sChannel = new NotificationChannel(SECOND_CHANNEL,
                    "second_channel" ,NotificationManager.IMPORTANCE_HIGH);
            sChannel.enableLights(true);
            sChannel.enableVibration(false);
            sChannel.setLightColor(Color.BLUE);
            sChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            getManager().createNotificationChannel(sChannel);

        }
    }

    public Notification.Builder getDefaultNotification(String title, String body) {
        long [] vibrateTime = {500, 2000};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new Notification.Builder(getApplicationContext(), DEFAULT_CHANNEL)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setSmallIcon(getSmallIcon())
                    .setAutoCancel(true);
        } else {
            return new Notification.Builder(getApplicationContext())
                    .setLights(Color.GREEN, 500, 500)
                    .setVibrate(vibrateTime)
                    .setVisibility(Notification.VISIBILITY_PRIVATE)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setSmallIcon(getSmallIcon())
                    .setAutoCancel(true);
        }
    }

    public Notification.Builder getSecondNotification(String title, String body) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new Notification.Builder(getApplicationContext(), SECOND_CHANNEL)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setSmallIcon(getSmallIcon())
                    .setAutoCancel(true);
        } else {
            return new Notification.Builder(getApplicationContext())
                    .setLights(Color.BLUE, 500, 500)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setSmallIcon(getSmallIcon())
                    .setAutoCancel(true);
        }
    }

    private int getSmallIcon() {
        return android.R.drawable.stat_notify_chat;
    }

    private NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }


    public void notify(int id, Notification notification){
        getManager().notify(id, notification);
    }

    public void cancle(int id){
        getManager().cancel(id);
    }
}
