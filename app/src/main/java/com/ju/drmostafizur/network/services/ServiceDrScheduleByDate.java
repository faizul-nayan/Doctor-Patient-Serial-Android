package com.ju.drmostafizur.network.services;

import com.ju.drmostafizur.network.response.ResponseDrScheduleByDay;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Faizul Haque Nayan on 19/07/17.
 */
public interface ServiceDrScheduleByDate {


    @Headers("Connection: close")
    @GET("dr_schedules/dr_schedule_by_day")
    Call<ResponseDrScheduleByDay> getDrScheduleInfo(@Query("day") String day);

}
