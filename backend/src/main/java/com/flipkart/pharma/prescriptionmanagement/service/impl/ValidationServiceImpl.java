package com.flipkart.pharma.prescriptionmanagement.service.impl;

import com.flipkart.pharma.prescriptionmanagement.common.Status;
import com.flipkart.pharma.prescriptionmanagement.common.Utils;
import com.flipkart.pharma.prescriptionmanagement.common.Utils.StringType;
import com.flipkart.pharma.prescriptionmanagement.domain.Validation;
import com.flipkart.pharma.prescriptionmanagement.exception.PmaException;
import com.flipkart.pharma.prescriptionmanagement.model.request.CheckValidationRequest;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreatePrescriptionValidationRequest;
import com.flipkart.pharma.prescriptionmanagement.model.request.InitiateValidationRequest;
import com.flipkart.pharma.prescriptionmanagement.model.response.CheckValidationResponse;
import com.flipkart.pharma.prescriptionmanagement.model.response.CreatePrescriptionValidationResponse;
import com.flipkart.pharma.prescriptionmanagement.model.response.InitiateValidationResponse;
import com.flipkart.pharma.prescriptionmanagement.repository.DoctorRepository;
import com.flipkart.pharma.prescriptionmanagement.repository.ValidationRepository;
import com.flipkart.pharma.prescriptionmanagement.service.OTPService;
import com.flipkart.pharma.prescriptionmanagement.service.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValidationServiceImpl implements ValidationService {
    private static final long PID_LENGTH = 10L;

    @Autowired
    ValidationRepository validationRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    OTPService otpService;

    @Override
    public CreatePrescriptionValidationResponse createPrescriptionValidationRecord(CreatePrescriptionValidationRequest createPrescriptionValidationRequest)throws PmaException {
        Validation validation = new Validation();
        CreatePrescriptionValidationResponse response = new CreatePrescriptionValidationResponse();
        try{
            if (doctorRepository.searchByDIN(createPrescriptionValidationRequest.getDocIdNo()) == null)
                response.setStatus(Status.FAILURE);
            do {
                this.setDomainAttributes(validation, createPrescriptionValidationRequest);
            }while(validationRepository.getByPresciptionId(validation.getPresciptionId()) != null);
            validationRepository.save(validation);
            response.setPrescriptionId(validation.getPresciptionId());
            response.setStatus(Status.SUCCESS);
        }
        catch(Exception e){
            response.setStatus(Status.FAILURE);
        }
        return response;
    }

    private void setDomainAttributes(Validation validation, CreatePrescriptionValidationRequest request) {
        validation.setDocIdNo(request.getDocIdNo());
        validation.setIssuedEmail(request.getPatientEmail());
        validation.setIssuedPhoneNo(request.getPatientPhoneNo());
        validation.setPresciptionId(Utils.uniqueString(PID_LENGTH, StringType.PID));
    }

    @Override
    public CheckValidationResponse checkValidation(CheckValidationRequest checkValidationRequest) {
        boolean result =  otpService.validateOTP(checkValidationRequest);
        CheckValidationResponse response = new CheckValidationResponse();
        response.setPrescriptionId(checkValidationRequest.getPrescriptionId());
        if(result)
            response.setStatus(Status.SUCCESS);
        else
            response.setStatus(Status.FAILURE);

        return response;
    }

    @Override
    public InitiateValidationResponse initiateValidation(InitiateValidationRequest initiateValidationRequest)throws PmaException {
        try {
            Validation validation = validationRepository.getByPresciptionId(initiateValidationRequest.getPrescriptionId());
            otpService.generateOTP(initiateValidationRequest.getPrescriptionId(), validation.getIssuedPhoneNo());
        }
        catch(PmaException e){
            log.error(e.getMessage());
            throw e;
        }
        InitiateValidationResponse response = new InitiateValidationResponse();
        response.setPid(initiateValidationRequest.getPrescriptionId());
        response.setStatus(Status.SUCCESS);
        return response;
    }
}
