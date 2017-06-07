package com.dev.prepcarapplication.bean;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by this on 2/8/2017.
 */

public class NewMatchesBean {

    public JSONArray getCarpices() {
        return carpices;
    }

    public void setCarpices(JSONArray carpices) {
        this.carpices = carpices;
    }

    JSONArray carpices;
    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    int car_id;

    public void setDealer_id(int dealer_id) {
        this.dealer_id = dealer_id;
    }
    public int getDealer_id() {
        return dealer_id;
    }
    int dealer_id;

    public int getDeal_id() {
        return deal_id;
    }

    public void setDeal_id(int deal_id) {
        this.deal_id = deal_id;
    }

    int deal_id;

    public String getCar_pic() {
        return car_pic;
    }

    public void setCar_pic(String car_pic) {
        this.car_pic = car_pic;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDealership_name() {
        return dealership_name;
    }

    public void setDealership_name(String dealership_name) {
        this.dealership_name = dealership_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExterior_color() {
        return exterior_color;
    }

    public void setExterior_color(String exterior_color) {
        this.exterior_color = exterior_color;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel_type() {
        return model_type;
    }

    public void setModel_type(String model_type) {
        this.model_type = model_type;
    }

    public String getModel_year() {
        return model_year;
    }

    public void setModel_year(String model_year) {
        this.model_year = model_year;
    }


    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStock_number() {
        return stock_number;
    }

    public void setStock_number(String stock_number) {
        this.stock_number = stock_number;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    String car_pic;
    String city;
    String country;
    String dealership_name;
    String email;
    String exterior_color;
    String first_name;
    String last_name;
    String make;
    String model_type;
    String model_year;
    String profile_picture;
    String state;
    String stock_number;
    String transmission;
    String username;
    String zipcode;
    String terms;
    String disclaimer;
    String months;
    String interestRate;
    String salesTaxRate;
    String downPayment;
    String tradeInValue;
    String bestPrice;
    String carplanId;
    JSONArray coolFeatures;
    String nickname;
    String monthlyPayment;
    String date;
    String test_drive_status;

    public String getTest_drive_status() {
        return test_drive_status;
    }

    public void setTest_drive_status(String test_drive_status) {
        this.test_drive_status = test_drive_status;
    }

    public int getRating_id() {
        return rating_id;
    }

    public void setRating_id(int rating_id) {
        this.rating_id = rating_id;
    }

    int rating_id;

    public int getDeal_expire() {
        return deal_expire;
    }

    public void setDeal_expire(int deal_expire) {
        this.deal_expire = deal_expire;
    }

    int deal_expire;
    boolean isClicked;

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public String getDealerPhone() {
        return dealerPhone;
    }

    public void setDealerPhone(String dealerPhone) {
        this.dealerPhone = dealerPhone;
    }

    String dealerPhone;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(String monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public JSONArray getCoolFeatures() {
        return coolFeatures;
    }

    public void setCoolFeatures(JSONArray coolFeatures) {
        this.coolFeatures = coolFeatures;
    }

    public String getCarplanId() {
        return carplanId;
    }

    public void setCarplanId(String carplanId) {
        this.carplanId = carplanId;
    }

    public String getBestPrice() {
        return bestPrice;
    }

    public void setBestPrice(String bestPrice) {
        this.bestPrice = bestPrice;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getSalesTaxRate() {
        return salesTaxRate;
    }

    public void setSalesTaxRate(String salesTaxRate) {
        this.salesTaxRate = salesTaxRate;
    }

    public String getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(String downPayment) {
        this.downPayment = downPayment;
    }

    public String getTradeInValue() {
        return tradeInValue;
    }

    public void setTradeInValue(String tradeInValue) {
        this.tradeInValue = tradeInValue;
    }

    public String getAmountOnTrade() {
        return amountOnTrade;
    }

    public void setAmountOnTrade(String amountOnTrade) {
        this.amountOnTrade = amountOnTrade;
    }

    String amountOnTrade;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String description;

    public ArrayList<String> getCarImages() {
        return carImages;
    }

    public void setCarImages(ArrayList<String> carImages) {
        this.carImages = carImages;
    }

    ArrayList<String> carImages;
    public String getSingle_car_pic() {
        return single_car_pic;
    }

    public void setSingle_car_pic(String single_car_pic) {
        this.single_car_pic = single_car_pic;
    }

    String single_car_pic;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    int rating;

    public boolean isSetRating() {
        return setRating;
    }

    public void setSetRating(boolean setRating) {
        this.setRating = setRating;
    }

    boolean setRating;
}
