package com.dev.prepcarapplication.bean;

import java.io.Serializable;

/**
 * Created by this on 2/21/2017.
 */

public class TestDriveModel implements Serializable {

    private int testdrive_id,confirm_status,appointment_status;
    private String dealer_id,buyer_id,deal_id,date,time,car_id,model_year,model_type,make,buyer_name,single_car_pic;

    public int getTestdrive_id() {
        return testdrive_id;
    }

    public void setTestdrive_id(int testdrive_id) {
        this.testdrive_id = testdrive_id;
    }

    public int getAppointment_status() {
        return appointment_status;
    }

    public void setAppointment_status(int appointment_status) {
        this.appointment_status = appointment_status;
    }
    public int getConfirm_status() {
        return confirm_status;
    }

    public void setConfirm_status(int confirm_status) {
        this.confirm_status = confirm_status;
    }

    public String getDealer_id() {
        return dealer_id;
    }

    public void setDealer_id(String dealer_id) {
        this.dealer_id = dealer_id;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getDeal_id() {
        return deal_id;
    }

    public void setDeal_id(String deal_id) {
        this.deal_id = deal_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getModel_year() {
        return model_year;
    }

    public void setModel_year(String model_year) {
        this.model_year = model_year;
    }

    public String getModel_type() {
        return model_type;
    }

    public void setModel_type(String model_type) {
        this.model_type = model_type;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public String getSingle_car_pic() {
        return single_car_pic;
    }

    public void setSingle_car_pic(String single_car_pic) {
        this.single_car_pic = single_car_pic;
    }
}