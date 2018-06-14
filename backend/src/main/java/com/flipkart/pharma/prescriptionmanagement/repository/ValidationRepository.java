package com.flipkart.pharma.prescriptionmanagement.repository;

import com.flipkart.pharma.prescriptionmanagement.domain.Prescription;
import org.springframework.data.repository.CrudRepository;

public interface ValidationRepository extends CrudRepository<Prescription, Long> {
    public Prescription getByPresciptionId(String prescriptionId);
}