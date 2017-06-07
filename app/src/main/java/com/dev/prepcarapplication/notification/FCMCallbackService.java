package com.dev.prepcarapplication.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.dev.prepcarapplication.CarPlanActivity;
import com.dev.prepcarapplication.NavigateActivityDealer;
import com.dev.prepcarapplication.NotificationActivityBuyer;
import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.TestDriveActivityDealer;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.preference.MySharedPreferences;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by shubham on 2/20/2017.
 */

public class FCMCallbackService extends FirebaseMessagingService {
    private static final String TAG = "FCMCallbackService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        /*Log.d(TAG, "From:" + remoteMessage.getFrom() + "");
        Log.e(TAG, "onMessageReceived: " + remoteMessage.getData().get("role") + "  " + remoteMessage.getData().get("title") + "  " + remoteMessage.getData().get("body")
                + remoteMessage.getData().get("notificationtype"));*/
        if (MySharedPreferences.getInstance().getBoolean(getApplicationContext(), Constants.ISREMEMBER, false))
            sendNotification(remoteMessage.getData());

    }

    private void sendNotification(Map<String, String> notification) {
        int color = getResources().getColor(R.color.com_facebook_blue);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent intent = null;
        try {
            if (notification.get("role").equalsIgnoreCase("3")) {
                if (notification.get("notificationtype").equalsIgnoreCase("6")) {
                    intent = new Intent(this, NotificationActivityBuyer.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                } else if (notification.get("notificationtype").equalsIgnoreCase("4")) {
                    intent = new Intent(this, TestDriveActivityDealer.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                } else {
                    intent = new Intent(this, NavigateActivityDealer.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
            } else {
                intent = new Intent(this, CarPlanActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            }

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            if (MySharedPreferences.getInstance().getInteger(getApplicationContext(), Constants.role_id, 0)
                    == Integer.parseInt(notification.get("role"))) {
                MySharedPreferences.getInstance().putBooleanKeyValue(getApplicationContext(), Constants.isUnreadNotification, true);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        .setContentTitle(notification.get("title"))
                        .setContentText(notification.get("body"))
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.logo_icon)
                        .setColor(color)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(notification.get("body")))
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify((int) System.currentTimeMillis(), builder.build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}