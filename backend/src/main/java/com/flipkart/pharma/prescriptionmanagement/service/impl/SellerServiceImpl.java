package com.flipkart.pharma.prescriptionmanagement.service.impl;

import com.flipkart.pharma.prescriptionmanagement.client.SMSClient;
import com.flipkart.pharma.prescriptionmanagement.domain.Doctor;
import com.flipkart.pharma.prescriptionmanagement.domain.Prescription;
import com.flipkart.pharma.prescriptionmanagement.domain.Seller;
import com.flipkart.pharma.prescriptionmanagement.exception.PmaException;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreateSellerRequest;
import com.flipkart.pharma.prescriptionmanagement.repository.DoctorRepository;
import com.flipkart.pharma.prescriptionmanagement.repository.PrescriptionMedicineMappingRepository;
import com.flipkart.pharma.prescriptionmanagement.repository.SellerRepository;
import com.flipkart.pharma.prescriptionmanagement.repository.ValidationRepository;
import com.flipkart.pharma.prescriptionmanagement.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sourabh.d on 14/06/18.
 */

@Component
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ValidationRepository validationRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Seller create(CreateSellerRequest request) throws PmaException {
        Seller seller = sellerRepository.findBySid(request.getSid());
        if(seller != null) {
            throw new PmaException("Seller with sid : " + request.getSid() + " already registered");
        }

        try {
            seller = new Seller();
            this.setDomainAttributes(seller, request);
            sellerRepository.save(seller);
            return seller;
        } catch (Exception e) {
            throw new PmaException("Error while adding new seller");
        }
    }

    @Override
    public void updatePurchase(String pid) throws PmaException {
        Prescription prescription = validationRepository.getByPrescriptionId(pid);
        if(prescription != null) {
            throw new PmaException("No prescription found");
        }

        if(prescription.getMaxPurchase() != null && prescription.getMaxPurchase() <= prescription.getPurchaseCount()) {
            String message = "Patient+purchase+exceeds+count+with+prescriptionId+" + prescription.getPrescriptionId()+"+please+check";
            Doctor doctor = doctorRepository.searchByDIN(prescription.getDocIdNo());
            SMSClient.sendSMS(message, doctor.getPhone());
            throw new PmaException("Maximum purchase count exceeds");
        }

        if(prescription.getIsPurchased()) {
            prescription.setPurchaseCount(prescription.getPurchaseCount() + 1);
        }
        prescription.setIsPurchased(true);
        validationRepository.save(prescription);
    }

    private void setDomainAttributes(Seller seller, CreateSellerRequest request) {
        seller.setName(request.getName());
        seller.setAddress(request.getAddress());
        seller.setSid(request.getSid());
        seller.setPhoneNo(request.getPhoneNumber());
    }
}
