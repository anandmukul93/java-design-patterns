package com.flipkart.pharma.prescriptionmanagement.service;

import com.flipkart.pharma.prescriptionmanagement.exception.PmaException;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreatePrescriptionRequest;
import com.flipkart.pharma.prescriptionmanagement.domain.PrescriptionMedicineMapper;
import com.flipkart.pharma.prescriptionmanagement.model.response.PrescriptionResponse;

import java.util.List;

/**
 * Created by sourabh.d on 14/06/18.
 */
public interface PrescriptionService {
    PrescriptionMedicineMapper create(CreatePrescriptionRequest request) throws PmaException;
    List<PrescriptionResponse> getPrescription(String pid) throws PmaException;
}
