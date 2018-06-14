package com.flipkart.pharma.prescriptionmanagement.service.impl;

import com.flipkart.pharma.prescriptionmanagement.client.SMSClient;
import com.flipkart.pharma.prescriptionmanagement.common.Utils;
import com.flipkart.pharma.prescriptionmanagement.common.Utils.StringType;
import com.flipkart.pharma.prescriptionmanagement.domain.OTP;
import com.flipkart.pharma.prescriptionmanagement.exception.PmaException;
import com.flipkart.pharma.prescriptionmanagement.model.request.CheckValidationRequest;
import com.flipkart.pharma.prescriptionmanagement.repository.OTPRepository;
import com.flipkart.pharma.prescriptionmanagement.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OTPServiceImpl  implements OTPService {
    private static final Long OTP_LENGTH = 4L;
    @Autowired
    private OTPRepository otpRepository;

    public void generateOTP(String documentId, String beneficiaryNo)throws PmaException{
        OTP otp = new OTP();
        try {
            OTP oldValidOTP = otpRepository.findByPrescriptionIdAndIsValid(documentId, true);
            if(oldValidOTP != null){
                oldValidOTP.setIsValid(false);
                otpRepository.save(oldValidOTP);
            }
            this.setDomainAttributes(otp, documentId);
            otpRepository.save(otp);
        }
        catch(Exception e){
            throw new PmaException("error generating OTP for pid : " + documentId);
        }
        String smsString = String.format("Your OTP for prescription validation is : %s", otp.getOTP());
        SMSClient.sendSMS(smsString,beneficiaryNo);
    }

    private void setDomainAttributes(OTP otp, String documentId) {
        otp.setPrescriptionId(documentId);
        otp.setEpxirationTime(new Date(System.currentTimeMillis()+15*60*1000)); //15 mins expiration time
        otp.setIsValid(true);
        otp.setOTP(Utils.uniqueString(OTP_LENGTH, StringType.OTP));
    }

    public  boolean validateOTP(CheckValidationRequest request) {
        OTP otp = otpRepository.findByPrescriptionIdAndIsValid(request.getPrescriptionId(), true);
        if(otp != null){
            return otp.getEpxirationTime().equals(request.getValidationTimeStamp()) || otp.getEpxirationTime().after(request.getValidationTimeStamp());
        }
        return false;
    }
}
