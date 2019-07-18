package com.ju.drmostafizur.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Faizul Haque Nayan on 19/07/14.
 */
public class RESTDrSchedule {

    @SerializedName("id")
    private int id;
    @SerializedName("hospital_name")
    private String hospitalName;
    @SerializedName("employeer_type")
    private String employeeType;
    @SerializedName("max_visitor")
    private int maxPatient;
    @SerializedName("time_from")
    private String timeFrom;
    @SerializedName("time_to")
    private String timeTo;
    @SerializedName("is_disable")
    private int isDisable;
    @SerializedName("day")
    private String day;
    @SerializedName("address")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public int getMaxPatient() {
        return maxPatient;
    }

    public void setMaxPatient(int maxPatient) {
        this.maxPatient = maxPatient;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public int getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(int isDisable) {
        this.isDisable = isDisable;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "RESTDrSchedule{" +
                "id=" + id +
                ", hospitalName='" + hospitalName + '\'' +
                ", employeeType='" + employeeType + '\'' +
                ", maxPatient=" + maxPatient +
                ", timeFrom='" + timeFrom + '\'' +
                ", timeTo='" + timeTo + '\'' +
                ", isDisable=" + isDisable +
                ", day='" + day + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
