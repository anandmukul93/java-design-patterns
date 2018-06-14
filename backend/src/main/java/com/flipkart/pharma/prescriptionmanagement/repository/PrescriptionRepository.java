package com.flipkart.pharma.prescriptionmanagement.repository;

import com.flipkart.pharma.prescriptionmanagement.domain.Prescription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by sourabh.d on 14/06/18.
 */
public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
    List<Prescription> getByPid(String pid);
}
