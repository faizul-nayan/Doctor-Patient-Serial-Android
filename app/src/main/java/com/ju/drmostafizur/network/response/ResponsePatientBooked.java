package com.ju.drmostafizur.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Faizul Haque Nayan on 19/07/16.
 */
public class ResponsePatientBooked extends BaseResponse {

    @SerializedName("serial")
    private int serial;
    @SerializedName("token")
    private String tocken;

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getTocken() {
        return tocken;
    }

    public void setTocken(String tocken) {
        this.tocken = tocken;
    }
}
