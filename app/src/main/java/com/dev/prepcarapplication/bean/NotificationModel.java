package com.dev.prepcarapplication.bean;

/**
 * Created by this on 2/21/2017.
 */

public class NotificationModel {

    public int notificationId;
    public String userId;
    public String notificationType;
    public String message;
    public String carplanId;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String time;

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCarplanId() {
        return carplanId;
    }

    public void setCarplanId(String carplanId) {
        this.carplanId = carplanId;
    }


}
