package com.ju.drmostafizur.domain.model;

/**
 * Created by Faizul Haque Nayan on 19/07/14.
 */
public class DrSchedule {

    private int id;
    private String hospitalName;
    private String employeeType;
    private int maxPatient;
    private String timeFrom;
    private String timeTo;
    private int isDisable;
    private String day;
    private String address;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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

    @Override
    public String toString() {
        return "DrSchedule{" +
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
