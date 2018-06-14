package com.flipkart.pharma.prescriptionmanagement.service;

import com.flipkart.pharma.prescriptionmanagement.model.request.CreatePrescriptionRequest;
import com.flipkart.pharma.prescriptionmanagement.domain.Prescription;

/**
 * Created by sourabh.d on 14/06/18.
 */
public interface PrescriptionService {
    Prescription create(CreatePrescriptionRequest request);
}
