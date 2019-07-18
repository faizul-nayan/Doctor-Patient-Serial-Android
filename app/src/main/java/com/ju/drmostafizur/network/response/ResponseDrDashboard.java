package com.ju.drmostafizur.network.response;

import com.google.gson.annotations.SerializedName;
import com.ju.drmostafizur.network.model.RESTDrDashboardPatientModel;
import com.ju.drmostafizur.network.model.RESTDrInfoModel;
import com.ju.drmostafizur.network.model.RESTDrSchedule;

import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/14.
 */
public class ResponseDrDashboard extends BaseResponse {

    @SerializedName("info")
    private RESTDrInfoModel infoModel;
    @SerializedName("schedule")
    private List<RESTDrSchedule> scheduleList;
    @SerializedName("patient")
    private List<RESTDrDashboardPatientModel> patientModelList;

    public List<RESTDrDashboardPatientModel> getPatientModelList() {
        return patientModelList;
    }

    public void setPatientModelList(List<RESTDrDashboardPatientModel> patientModelList) {
        this.patientModelList = patientModelList;
    }

    public List<RESTDrSchedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<RESTDrSchedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public RESTDrInfoModel getInfoModel() {
        return infoModel;
    }

    public void setInfoModel(RESTDrInfoModel infoModel) {
        this.infoModel = infoModel;
    }
}
