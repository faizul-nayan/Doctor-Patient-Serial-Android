package com.ju.drmostafizur.domain.model;

/**
 * Created by Faizul Haque Nayan on 19/07/07.
 */

public class Patient {

    private int id;
    private String name;
    private String fatherSpouseName;
    private int age;
    private String phoneNumber;
    private String address;
    private String problem;
    private boolean isPaid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherSpouseName() {
        return fatherSpouseName;
    }

    public void setFatherSpouseName(String fatherSpouseName) {
        this.fatherSpouseName = fatherSpouseName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
