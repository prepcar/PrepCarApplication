package com.dev.prepcarapplication.notification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by this on 2/20/2017.
 */

public class FCMInitializationService extends FirebaseInstanceIdService {
    private static final String TAG = "FCMItoken==";

    @Override
    public void onTokenRefresh() {
        String fcmToken = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG,fcmToken);
        //Save or send FCM registration token
    }
}