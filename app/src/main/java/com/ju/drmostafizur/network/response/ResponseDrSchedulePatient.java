package com.ju.drmostafizur.network.response;

import com.google.gson.annotations.SerializedName;
import com.ju.drmostafizur.network.model.RESTDrSchedule;

import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/16.
 */
public class ResponseDrSchedulePatient extends BaseResponse {

    @SerializedName("schedule")
    private List<RESTDrSchedule> drScheduleList;
    @SerializedName("serial")
    private int serial;

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public List<RESTDrSchedule> getDrScheduleList() {
        return drScheduleList;
    }

    public void setDrScheduleList(List<RESTDrSchedule> drScheduleList) {
        this.drScheduleList = drScheduleList;
    }
}
