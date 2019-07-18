package com.ju.drmostafizur.network.services;

import com.ju.drmostafizur.network.response.ResponseDrHistory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Faizul Haque Nayan on 19/07/18.
 */
public interface ServiceDrHistory {

    @Headers("Connection: close")
    @GET("dr_dashboards/patient_history_by_day")
    Call<ResponseDrHistory> getDrHistoryByDay(@Query("date") String date);
}
