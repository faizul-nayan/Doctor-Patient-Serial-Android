package com.ju.drmostafizur.storage.converters;

import com.ju.drmostafizur.domain.model.PatientBookedModel;
import com.ju.drmostafizur.network.model.RESTPatientBookedModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faizul Haque Nayan on 19/07/16.
 */
public class PatientBookedConverter {

    public static PatientBookedModel convertRestToDoaminModel(RESTPatientBookedModel restPatientBookedModel) {

        PatientBookedModel patientBookedModel = new PatientBookedModel();
        patientBookedModel.setId(restPatientBookedModel.getId());
        patientBookedModel.setFullName(restPatientBookedModel.getFullName());
        patientBookedModel.setFatherSpouseName(restPatientBookedModel.getFatherSpouseName());
        patientBookedModel.setAddress(restPatientBookedModel.getAddress());
        patientBookedModel.setPhoneNumber(restPatientBookedModel.getPhoneNumber());
        patientBookedModel.setEmail(restPatientBookedModel.getEmail());
        patientBookedModel.setAge(restPatientBookedModel.getAge());
        patientBookedModel.setGender(restPatientBookedModel.getGender());
        patientBookedModel.setBloodGroup(restPatientBookedModel.getBloodGroup());

        patientBookedModel.setPayVaiCash(restPatientBookedModel.getPayVaiCash());
        patientBookedModel.setPaymentOnlien(restPatientBookedModel.getPaymentOnlien());
        patientBookedModel.setPayByBKash(restPatientBookedModel.getPayByBKash());
        patientBookedModel.setbKashRecNo(restPatientBookedModel.getbKashRecNo());
        patientBookedModel.setPayOther(restPatientBookedModel.getPayOther());
        patientBookedModel.setOtherRef(restPatientBookedModel.getOtherRef());

        patientBookedModel.setDay(restPatientBookedModel.getDay());
        patientBookedModel.setDate(restPatientBookedModel.getDate());

        return patientBookedModel;
    }

    public static RESTPatientBookedModel convertDomainToRestModel(PatientBookedModel patientBookedModel) {

        RESTPatientBookedModel restPatientBookedModel = new RESTPatientBookedModel();
        restPatientBookedModel.setId(patientBookedModel.getId());
        restPatientBookedModel.setFullName(patientBookedModel.getFullName());
        restPatientBookedModel.setFatherSpouseName(patientBookedModel.getFatherSpouseName());
        restPatientBookedModel.setAddress(patientBookedModel.getAddress());
        restPatientBookedModel.setPhoneNumber(patientBookedModel.getPhoneNumber());
        restPatientBookedModel.setEmail(patientBookedModel.getEmail());
        restPatientBookedModel.setAge(patientBookedModel.getAge());
        restPatientBookedModel.setGender(patientBookedModel.getGender());
        restPatientBookedModel.setBloodGroup(patientBookedModel.getBloodGroup());

        restPatientBookedModel.setPayVaiCash(patientBookedModel.getPayVaiCash());
        restPatientBookedModel.setPaymentOnlien(patientBookedModel.getPaymentOnlien());
        restPatientBookedModel.setPayByBKash(patientBookedModel.getPayByBKash());
        restPatientBookedModel.setbKashRecNo(patientBookedModel.getbKashRecNo());
        restPatientBookedModel.setPayOther(patientBookedModel.getPayOther());
        restPatientBookedModel.setOtherRef(patientBookedModel.getOtherRef());

        restPatientBookedModel.setDay(patientBookedModel.getDay());
        restPatientBookedModel.setDate(patientBookedModel.getDate());
        return restPatientBookedModel;
    }




    public static List<PatientBookedModel> convertRestListToDoaminModel(List<RESTPatientBookedModel> restLoans) {

        List<PatientBookedModel> loans = new ArrayList<>();

        for (RESTPatientBookedModel rest : restLoans) {
            loans.add(convertRestToDoaminModel(rest));
        }

        // cleanup
        restLoans.clear();

        return loans;
    }

    public static List<RESTPatientBookedModel> convertDomainListToRestModel(List<PatientBookedModel> loans) {

        List<RESTPatientBookedModel> restLoans = new ArrayList<>();

        for (PatientBookedModel loan : loans) {
            restLoans.add(convertDomainToRestModel(loan));
        }

        // cleanup
        loans.clear();

        return restLoans;
    }
}
