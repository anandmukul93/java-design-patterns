package com.flipkart.pharma.prescriptionmanagement.service.impl;

import com.flipkart.pharma.prescriptionmanagement.model.request.CheckValidationRequest;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreatePrescriptionValidationRequest;
import com.flipkart.pharma.prescriptionmanagement.model.request.InitiateValidationRequest;
import com.flipkart.pharma.prescriptionmanagement.model.response.CheckValidationResponse;
import com.flipkart.pharma.prescriptionmanagement.model.response.CreatePrescriptionValidationResponse;
import com.flipkart.pharma.prescriptionmanagement.model.response.InitiateValidationResponse;
import com.flipkart.pharma.prescriptionmanagement.repository.ValidationRepository;
import com.flipkart.pharma.prescriptionmanagement.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    ValidationRepository validationRepository;

    @Override
    public CreatePrescriptionValidationResponse createPrescriptionValidationRecord(CreatePrescriptionValidationRequest createPrescriptionValidationRequest) {
        return null;
    }

    @Override
    public CheckValidationResponse checkValidation(CheckValidationRequest checkValidationRequest) {
        return null;
    }

    @Override
    public InitiateValidationResponse initiateValidation(InitiateValidationRequest initiateValidationRequest) {
        return null;
    }
}
