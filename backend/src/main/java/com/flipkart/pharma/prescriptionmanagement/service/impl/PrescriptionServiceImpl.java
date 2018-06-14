package com.flipkart.pharma.prescriptionmanagement.service.impl;

import com.flipkart.pharma.prescriptionmanagement.domain.Medicine;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreatePrescriptionRequest;
import com.flipkart.pharma.prescriptionmanagement.domain.Prescription;
import com.flipkart.pharma.prescriptionmanagement.repository.MedicineRepository;
import com.flipkart.pharma.prescriptionmanagement.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by sourabh.d on 14/06/18.
 */
@Component
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Prescription create(CreatePrescriptionRequest request) {
        Prescription prescription = new Prescription();
        setDomainAttributes(prescription, request);
        return prescription;
    }

    private void setDomainAttributes(Prescription prescription, CreatePrescriptionRequest request) {
        Optional<Medicine> medicine = medicineRepository.findById(request.getMedicineId());
        if(medicine == null) {

        }
        prescription.setPid(request.getPid());
        prescription.setQuantity(request.getQuantity());
        prescription.setRemarks(request.getRemarks());
        prescription.setType(request.getType());
    }
}
