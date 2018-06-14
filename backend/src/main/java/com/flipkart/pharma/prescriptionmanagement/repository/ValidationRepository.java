package com.flipkart.pharma.prescriptionmanagement.repository;

import com.flipkart.pharma.prescriptionmanagement.domain.Validation;
import org.springframework.data.repository.CrudRepository;

public interface ValidationRepository extends CrudRepository<Validation, Long> {
}