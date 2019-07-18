package com.ju.drmostafizur.presentation.model;

/**
 * Created by Faizul Haque Nayan on 19/07/09.
 */
public class DayModel {

    private String dayName;
    private int icon;

    public DayModel(String dayName, int icon) {
        this.dayName = dayName;
        this.icon = icon;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
