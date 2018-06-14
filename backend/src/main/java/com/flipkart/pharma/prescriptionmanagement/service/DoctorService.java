package com.flipkart.pharma.prescriptionmanagement.service;

import com.flipkart.pharma.prescriptionmanagement.exception.DoctorException;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreateDoctorRequest;
import com.flipkart.pharma.prescriptionmanagement.model.response.DoctorResponse;
import com.flipkart.pharma.prescriptionmanagement.domain.Doctor;

public interface DoctorService {
    public DoctorResponse getDoctor(String DIN);
    public Doctor createDoctor(CreateDoctorRequest doctorRequest) throws DoctorException;
}
