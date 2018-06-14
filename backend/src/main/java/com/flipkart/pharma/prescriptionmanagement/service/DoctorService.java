package com.flipkart.pharma.prescriptionmanagement.service;

import com.flipkart.pharma.prescriptionmanagement.model.request.CreateDoctorRequest;
import com.flipkart.pharma.prescriptionmanagement.model.response.DoctorResponse;

public interface DoctorService {
    public DoctorResponse getDoctor(String DIN);
    public void createDoctor(CreateDoctorRequest doctorRequest);
}
