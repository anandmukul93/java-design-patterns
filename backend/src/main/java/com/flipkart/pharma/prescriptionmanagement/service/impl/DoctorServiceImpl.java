package com.flipkart.pharma.prescriptionmanagement.service.impl;

import com.flipkart.pharma.prescriptionmanagement.domain.Doctor;
import com.flipkart.pharma.prescriptionmanagement.exception.DoctorException;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreateDoctorRequest;
import com.flipkart.pharma.prescriptionmanagement.model.response.DoctorResponse;
import com.flipkart.pharma.prescriptionmanagement.repository.DoctorRepository;
import com.flipkart.pharma.prescriptionmanagement.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public DoctorResponse getDoctor(String DIN) {
        return new DoctorResponse();
    }

    private void setDomainAttributes(Doctor doctor, CreateDoctorRequest request) {
        doctor.setName(request.getName());
        doctor.setDIN(request.getDIN());
        doctor.setPhone(request.getPhone());
    }

    @Override
    public Doctor createDoctor(CreateDoctorRequest doctorRequest) throws DoctorException {
        Doctor doctor = new Doctor();
        this.setDomainAttributes(doctor, doctorRequest);
        try {
            doctorRepository.save(doctor);
            return doctor;
        } catch (Exception e) {
            throw new DoctorException("Error while adding new doctor");
        }
    }
}
