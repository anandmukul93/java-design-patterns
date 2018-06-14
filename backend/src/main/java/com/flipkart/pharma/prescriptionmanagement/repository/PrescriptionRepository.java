package com.flipkart.pharma.prescriptionmanagement.repository;

import com.flipkart.pharma.prescriptionmanagement.domain.Prescription;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sourabh.d on 14/06/18.
 */
public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
}
