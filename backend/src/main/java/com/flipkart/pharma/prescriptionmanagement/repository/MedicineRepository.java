package com.flipkart.pharma.prescriptionmanagement.repository;

import com.flipkart.pharma.prescriptionmanagement.domain.Medicine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sourabh.d on 14/06/18.
 */
public interface MedicineRepository extends CrudRepository<Medicine, Long> {
}
