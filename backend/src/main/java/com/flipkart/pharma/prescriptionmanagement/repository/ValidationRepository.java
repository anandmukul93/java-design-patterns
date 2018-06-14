package com.flipkart.pharma.prescriptionmanagement.repository;

import com.flipkart.pharma.prescriptionmanagement.domain.Prescription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ValidationRepository extends CrudRepository<Prescription, Long> {
    Prescription getByPresciptionId(String prescriptionId);
    List<Prescription> getByIsPurchased(Boolean isPurchased);
}