package com.ju.drmostafizur.network.requests;

import com.google.gson.annotations.SerializedName;
import com.ju.drmostafizur.network.model.RESTDrSchedule;

/**
 * Created by Faizul Haque Nayan on 19/07/14.
 */
public class RequestDrNewSchedule {
    @SerializedName("schedule")
    private RESTDrSchedule schedule;

    public RESTDrSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(RESTDrSchedule schedule) {
        this.schedule = schedule;
    }
}
