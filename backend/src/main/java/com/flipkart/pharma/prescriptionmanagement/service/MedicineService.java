package com.flipkart.pharma.prescriptionmanagement.service;

import com.flipkart.pharma.prescriptionmanagement.domain.Medicine;
import com.flipkart.pharma.prescriptionmanagement.exception.PmaException;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreateMedicineRequest;

import java.util.List;

/**
 * Created by sourabh.d on 14/06/18.
 */
public interface MedicineService {
    Medicine create(CreateMedicineRequest request) throws PmaException;
    List<Medicine> search(String name) throws PmaException;
}
