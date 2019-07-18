package com.ju.drmostafizur.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Faizul Haque Nayan on 19/07/17.
 */
public class RESTDrDashboardPatientModel {

    @SerializedName("id")
    private int id;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("payment")
    private String payment;
    @SerializedName("age")
    private int age;
    @SerializedName("serial")
    private int serial;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }
}
