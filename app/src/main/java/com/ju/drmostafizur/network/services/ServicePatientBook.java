package com.ju.drmostafizur.network.services;

import com.ju.drmostafizur.network.requests.RequestPatientBooked;
import com.ju.drmostafizur.network.response.ResponsePatientBooked;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Faizul Haque Nayan on 19/07/16.
 */
public interface ServicePatientBook {

    @Headers("Connection: close")
    @POST("patient_info/booked_new_serial")
    Call<ResponsePatientBooked> setPatientBookedInfo(@Body RequestPatientBooked requestPatientBooked);
}
