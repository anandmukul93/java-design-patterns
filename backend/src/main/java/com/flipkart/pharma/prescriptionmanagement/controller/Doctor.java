package com.flipkart.pharma.prescriptionmanagement.controller;

import com.flipkart.pharma.prescriptionmanagement.datatypes.DoctorRequest;
import com.flipkart.pharma.prescriptionmanagement.datatypes.DoctorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class Doctor {
    public DoctorResponse getDoctor(String DIN) {
        return new DoctorResponse();
    }

    public void createDoctor(DoctorRequest doctorRequest) {

    }

}
