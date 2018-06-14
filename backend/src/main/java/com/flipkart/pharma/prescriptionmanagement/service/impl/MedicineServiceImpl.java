package com.flipkart.pharma.prescriptionmanagement.service.impl;

import com.flipkart.pharma.prescriptionmanagement.domain.Medicine;
import com.flipkart.pharma.prescriptionmanagement.exception.PmaException;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreateMedicineRequest;
import com.flipkart.pharma.prescriptionmanagement.repository.MedicineRepository;
import com.flipkart.pharma.prescriptionmanagement.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sourabh.d on 14/06/18.
 */
@Component
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Medicine create(CreateMedicineRequest request) throws PmaException {
        Medicine medicine = new Medicine();
        this.setDomainAttributes(medicine, request);
        try {
            medicineRepository.save(medicine);
            return medicine;
        } catch (Exception e) {
            throw new PmaException("Error while adding new medicine");
        }
    }

    @Override
    public List<Medicine> search(String name) throws PmaException {
        try {
            List<Medicine> medicines = medicineRepository.searchByName(name);
            return medicines;
        } catch (Exception e) {
            throw new PmaException("Error while searching medicines");
        }
    }

    private void setDomainAttributes(Medicine medicine, CreateMedicineRequest request) {
        medicine.setName(request.getMedicineName());
        medicine.setDescription(request.getDescription());
        medicine.setType(request.getType());
    }
}
