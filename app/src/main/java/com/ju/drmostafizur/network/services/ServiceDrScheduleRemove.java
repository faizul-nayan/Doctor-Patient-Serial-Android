package com.ju.drmostafizur.network.services;

import com.ju.drmostafizur.network.response.ResponseDrScheduleRemove;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Faizul Haque Nayan on 19/07/17.
 */
public interface ServiceDrScheduleRemove {

    @Headers("Connection: close")
    @GET("dr_schedules/remove")
    Call<ResponseDrScheduleRemove> getDrDashboardInfo(@Query("day") String day);
}
