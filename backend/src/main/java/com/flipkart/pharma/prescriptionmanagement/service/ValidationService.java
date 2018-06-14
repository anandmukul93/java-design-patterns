package com.flipkart.pharma.prescriptionmanagement.service;

import com.flipkart.pharma.prescriptionmanagement.exception.PmaException;
import com.flipkart.pharma.prescriptionmanagement.model.request.CheckValidationRequest;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreatePrescriptionValidationRequest;
import com.flipkart.pharma.prescriptionmanagement.model.request.InitiateValidationRequest;
import com.flipkart.pharma.prescriptionmanagement.model.response.CheckValidationResponse;
import com.flipkart.pharma.prescriptionmanagement.model.response.CreatePrescriptionValidationResponse;
import com.flipkart.pharma.prescriptionmanagement.model.response.InitiateValidationResponse;

public interface ValidationService {
    public CreatePrescriptionValidationResponse createPrescriptionValidationRecord(CreatePrescriptionValidationRequest createPrescriptionValidationRequest) throws PmaException;

    public CheckValidationResponse checkValidation(CheckValidationRequest checkValidationRequest) throws PmaException;

    public InitiateValidationResponse initiateValidation(InitiateValidationRequest initiateValidationRequest) throws PmaException;
}
