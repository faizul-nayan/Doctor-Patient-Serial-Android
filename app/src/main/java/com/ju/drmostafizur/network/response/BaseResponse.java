package com.ju.drmostafizur.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jahid on 3/1/2017
 * @copyright datasoft.
 */

public class BaseResponse {
    @SerializedName("status_code")
    private int statusCode;

    @SerializedName("message")
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
