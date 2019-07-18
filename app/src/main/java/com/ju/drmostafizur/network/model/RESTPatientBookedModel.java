package com.ju.drmostafizur.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Faizul Haque Nayan on 19/07/15.
 */
public class RESTPatientBookedModel {

    @SerializedName("id")
    private int id;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("father_spouse_name")
    private String fatherSpouseName;
    @SerializedName("address")
    private String address;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("email_address")
    private String email;
    @SerializedName("age")
    private int age;
    @SerializedName("gender")
    private String gender;
    @SerializedName("blood_group")
    private String bloodGroup;

    @SerializedName("pay_cash")
    private int payVaiCash;
    @SerializedName("pay_online")
    private int paymentOnlien;
    @SerializedName("pay_bKash")
    private int payByBKash;
    @SerializedName("bKash_rec_number")
    private String bKashRecNo;
    @SerializedName("pay_other")
    private int payOther;
    @SerializedName("other_ref")
    private String otherRef;

    @SerializedName("day")
    private String day;
    @SerializedName("date")
    private String date;

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

    public String getFatherSpouseName() {
        return fatherSpouseName;
    }

    public void setFatherSpouseName(String fatherSpouseName) {
        this.fatherSpouseName = fatherSpouseName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getPayVaiCash() {
        return payVaiCash;
    }

    public void setPayVaiCash(int payVaiCash) {
        this.payVaiCash = payVaiCash;
    }

    public int getPaymentOnlien() {
        return paymentOnlien;
    }

    public void setPaymentOnlien(int paymentOnlien) {
        this.paymentOnlien = paymentOnlien;
    }

    public int getPayByBKash() {
        return payByBKash;
    }

    public void setPayByBKash(int payByBKash) {
        this.payByBKash = payByBKash;
    }

    public String getbKashRecNo() {
        return bKashRecNo;
    }

    public void setbKashRecNo(String bKashRecNo) {
        this.bKashRecNo = bKashRecNo;
    }

    public int getPayOther() {
        return payOther;
    }

    public void setPayOther(int payOther) {
        this.payOther = payOther;
    }

    public String getOtherRef() {
        return otherRef;
    }

    public void setOtherRef(String otherRef) {
        this.otherRef = otherRef;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
