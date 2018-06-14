package com.flipkart.pharma.prescriptionmanagement.service;

import com.flipkart.pharma.prescriptionmanagement.exception.PmaException;
import com.flipkart.pharma.prescriptionmanagement.model.request.CheckValidationRequest;

public interface OTPService {
    public void generateOTP(String documentId, String beneficiaryNo) throws PmaException;
    public boolean validateOTP(CheckValidationRequest request);
}
