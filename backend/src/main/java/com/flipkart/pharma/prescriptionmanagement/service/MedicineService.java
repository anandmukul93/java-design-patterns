package com.flipkart.pharma.prescriptionmanagement.service;

import com.flipkart.pharma.prescriptionmanagement.domain.Medicine;
import com.flipkart.pharma.prescriptionmanagement.exception.PmaException;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreateMedicineRequest;

/**
 * Created by sourabh.d on 14/06/18.
 */
public interface MedicineService {
    Medicine create(CreateMedicineRequest request) throws PmaException;
}
