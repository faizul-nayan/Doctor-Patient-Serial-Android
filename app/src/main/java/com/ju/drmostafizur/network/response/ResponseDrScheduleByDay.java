package com.ju.drmostafizur.network.response;

import com.google.gson.annotations.SerializedName;
import com.ju.drmostafizur.domain.model.DrSchedule;
import com.ju.drmostafizur.network.model.RESTDrSchedule;

import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/17.
 */
public class ResponseDrScheduleByDay extends BaseResponse {

    @SerializedName("schedule")
    private List<RESTDrSchedule> scheduleList;

    public List<RESTDrSchedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<RESTDrSchedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
