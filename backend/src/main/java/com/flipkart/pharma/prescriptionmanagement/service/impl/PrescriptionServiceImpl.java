package com.flipkart.pharma.prescriptionmanagement.service.impl;

import com.flipkart.pharma.prescriptionmanagement.domain.Medicine;
import com.flipkart.pharma.prescriptionmanagement.exception.PmaException;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreatePrescriptionRequest;
import com.flipkart.pharma.prescriptionmanagement.domain.PrescriptionMedicineMapper;
import com.flipkart.pharma.prescriptionmanagement.model.response.PrescriptionResponse;
import com.flipkart.pharma.prescriptionmanagement.repository.MedicineRepository;
import com.flipkart.pharma.prescriptionmanagement.repository.PrescriptionRepository;
import com.flipkart.pharma.prescriptionmanagement.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by sourabh.d on 14/06/18.
 */
@Component
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;


    @Override
    public PrescriptionMedicineMapper create(CreatePrescriptionRequest request) throws PmaException {
        PrescriptionMedicineMapper prescription = new PrescriptionMedicineMapper();
        setDomainAttributes(prescription, request);
        prescriptionRepository.save(prescription);
        return prescription;
    }

    @Override
    public List<PrescriptionResponse> getPrescription(String pid) throws PmaException {
        List<PrescriptionMedicineMapper> prescriptions = prescriptionRepository.getByPid(pid);
        List<PrescriptionResponse> responses = new ArrayList<PrescriptionResponse>();
        for(PrescriptionMedicineMapper prescription : prescriptions) {

            PrescriptionResponse response  = PrescriptionResponse.builder()
                    .medicineName(prescription.getMedicine().getName())
                    .medicineType(prescription.getMedicine().getType())
                    .quantity(prescription.getQuantity())
                    .pid(prescription.getPid())
                    .remarks(prescription.getRemarks()).build();
            responses.add(response);
        }

        return responses;
    }

    private void setDomainAttributes(PrescriptionMedicineMapper prescription, CreatePrescriptionRequest request) throws PmaException {
        Optional<Medicine> medicine = medicineRepository.findById(request.getMedicineId());
        if(!medicine.isPresent()) {
            throw new PmaException("No such medicine found");
        }
        prescription.setMedicine(medicine.get());
        prescription.setPid(request.getPid());
        prescription.setQuantity(request.getQuantity());
        prescription.setRemarks(request.getRemarks());
    }
}
