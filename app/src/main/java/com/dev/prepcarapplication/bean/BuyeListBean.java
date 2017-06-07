package com.dev.prepcarapplication.bean;

/**
 * Created by this on 2/21/2017.
 */

public class BuyeListBean {
    String first_name;
    String last_name;
    String profile_picture;
    String city;
    String buyer_id;
    String carplan_id;

    public String getCarplan_id() {
        return carplan_id;
    }

    public void setCarplan_id(String carplan_id) {
        this.carplan_id = carplan_id;
    }

    public String getCarplan_status() {
        return carplan_status;
    }

    public void setCarplan_status(String carplan_status) {
        this.carplan_status = carplan_status;
    }

    String carplan_status;

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    String car_id
            ;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    String state;
}
