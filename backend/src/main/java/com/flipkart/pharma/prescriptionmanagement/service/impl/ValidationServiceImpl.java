package com.flipkart.pharma.prescriptionmanagement.service.impl;

import com.flipkart.pharma.prescriptionmanagement.common.Status;
import com.flipkart.pharma.prescriptionmanagement.common.Utils;
import com.flipkart.pharma.prescriptionmanagement.common.Utils.StringType;
import com.flipkart.pharma.prescriptionmanagement.domain.Prescription;
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
import com.flipkart.pharma.prescriptionmanagement.service.PrescriptionService;
import com.flipkart.pharma.prescriptionmanagement.service.ValidationService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@Slf4j
public class ValidationServiceImpl implements ValidationService {
    private static final long PID_LENGTH = 10L;

    @Autowired
    ValidationRepository validationRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PrescriptionService prescriptionService;

    @Autowired
    OTPService otpService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreatePrescriptionValidationResponse createPrescriptionValidationRecord(CreatePrescriptionValidationRequest createPrescriptionValidationRequest)throws PmaException {
        Prescription prescription = new Prescription();
        CreatePrescriptionValidationResponse response = new CreatePrescriptionValidationResponse();
        try{
            if (doctorRepository.searchByDIN(createPrescriptionValidationRequest.getDocIdNo()) == null)
                response.setStatus(Status.FAILURE);
            do {
                this.setDomainAttributes(prescription, createPrescriptionValidationRequest);
            }while(validationRepository.getByPrescriptionId(prescription.getPrescriptionId()) != null);
            response.setPrescriptionId(prescription.getPrescriptionId());
            validationRepository.save(prescription);
            prescriptionService.create(createPrescriptionValidationRequest.getMedicines(), prescription.getPrescriptionId());
            response.setStatus(Status.SUCCESS);
        }
        catch(Exception e){
            response.setStatus(Status.FAILURE);
        }
        return response;
    }

    private void setDomainAttributes(Prescription prescription, CreatePrescriptionValidationRequest request) {
        prescription.setDocIdNo(request.getDocIdNo());
        prescription.setIssuedEmail(request.getPatientEmail());
        prescription.setIssuedPhoneNo(request.getPatientPhoneNo());
        prescription.setPrescriptionId(Utils.uniqueString(PID_LENGTH, StringType.PID));
        prescription.setToNotify(request.getToNotify());
        prescription.setPurchaseCount(0);
        prescription.setExpiry(request.getExpiry());
        prescription.setMaxPurchase(request.getMaxPurchase());
    }

    @Override
    public CheckValidationResponse checkValidation(CheckValidationRequest checkValidationRequest) throws PmaException {
        Prescription prescription = validationRepository.getByPrescriptionId(checkValidationRequest.getPrescriptionId());
        Date currentDate = new Date(System.currentTimeMillis());
        if(prescription.getExpiry() != null && prescription.getExpiry().before(currentDate)) {
            throw new PmaException("Prescription date is expired");
        }

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
            Prescription prescription = validationRepository.getByPrescriptionId(initiateValidationRequest.getPrescriptionId());
            otpService.generateOTP(initiateValidationRequest.getPrescriptionId(), prescription.getIssuedPhoneNo());
        }
        catch(PmaException e){
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        InitiateValidationResponse response = new InitiateValidationResponse();
        response.setPid(initiateValidationRequest.getPrescriptionId());
        response.setStatus(Status.SUCCESS);
        return response;
    }
}
