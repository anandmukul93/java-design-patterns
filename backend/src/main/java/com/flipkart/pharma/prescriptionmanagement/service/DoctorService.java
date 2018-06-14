package com.flipkart.pharma.prescriptionmanagement.service;

import com.flipkart.pharma.prescriptionmanagement.domain.Doctor;
import com.flipkart.pharma.prescriptionmanagement.exception.DoctorException;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreateDoctorRequest;

public interface DoctorService {
    public Doctor search(String DIN) throws DoctorException;
    public Doctor createDoctor(CreateDoctorRequest doctorRequest) throws DoctorException;
}
