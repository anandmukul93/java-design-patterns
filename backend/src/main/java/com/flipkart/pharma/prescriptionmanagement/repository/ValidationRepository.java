package com.flipkart.pharma.prescriptionmanagement.repository;

import com.flipkart.pharma.prescriptionmanagement.domain.Validation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ValidationRepository extends CrudRepository<Validation, Long> {
    public Validation getByPresciptionId(String prescriptionId);
}