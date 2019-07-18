package com.ju.drmostafizur.network.services;

import com.ju.drmostafizur.network.response.ResponseDrSchedulePatient;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Faizul Haque Nayan on 19/07/16.
 */
public interface ServiceDrSchedulePatient {

    @Headers("Connection: close")
    @GET("patient_info/dr_schedule_by_day")
    Call<ResponseDrSchedulePatient> getDrScheduleInfo(@Query("day") String day, @Query("date") String date);

}
