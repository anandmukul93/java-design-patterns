package com.flipkart.pharma.prescriptionmanagement.service.impl;

import com.flipkart.pharma.prescriptionmanagement.model.request.CreateDoctorRequest;
import com.flipkart.pharma.prescriptionmanagement.model.response.DoctorResponse;
import com.flipkart.pharma.prescriptionmanagement.service.DoctorService;

public class DoctorServiceImpl implements DoctorService {
    public DoctorResponse getDoctor(String DIN) {
        return new DoctorResponse();
    }

    public void createDoctor(CreateDoctorRequest doctorRequest) {

    }
}
