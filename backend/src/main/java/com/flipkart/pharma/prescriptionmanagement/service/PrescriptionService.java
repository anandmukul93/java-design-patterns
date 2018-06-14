package com.flipkart.pharma.prescriptionmanagement.service;

import com.flipkart.pharma.prescriptionmanagement.exception.PmaException;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreatePrMedicineMappingRequest;
import com.flipkart.pharma.prescriptionmanagement.domain.PrescriptionMedicineMapper;
import com.flipkart.pharma.prescriptionmanagement.model.response.PrescriptionResponse;

import java.util.List;

/**
 * Created by sourabh.d on 14/06/18.
 */
public interface PrescriptionService {
    List<PrescriptionMedicineMapper> create(List<CreatePrMedicineMappingRequest> request, String pid) throws PmaException;
    List<PrescriptionResponse> getPrescription(String pid) throws PmaException;
}
