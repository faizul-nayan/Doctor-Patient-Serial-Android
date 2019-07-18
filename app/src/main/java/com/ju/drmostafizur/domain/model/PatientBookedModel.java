package com.ju.drmostafizur.domain.model;

/**
 * Created by Faizul Haque Nayan on 19/07/15.
 */
public class PatientBookedModel {

    private int id;
    private String fullName;
    private String fatherSpouseName;
    private String address;
    private String phoneNumber;
    private String email;
    private int age;
    private String gender;
    private String bloodGroup;

    private int payVaiCash;
    private int paymentOnlien;
    private int payByBKash;
    private String bKashRecNo;
    private int payOther;
    private String otherRef;

    private String day;
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

    public int getPayVaiCash() {
        return payVaiCash;
    }

    public void setPayVaiCash(int payVaiCash) {
        this.payVaiCash = payVaiCash;
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

    @Override
    public String toString() {
        return "PatientBookedModel{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", fatherSpouseName='" + fatherSpouseName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", payVaiCash=" + payVaiCash +
                ", paymentOnlien=" + paymentOnlien +
                ", payByBKash=" + payByBKash +
                ", bKashRecNo='" + bKashRecNo + '\'' +
                ", payOther=" + payOther +
                ", otherRef='" + otherRef + '\'' +
                ", day='" + day + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
