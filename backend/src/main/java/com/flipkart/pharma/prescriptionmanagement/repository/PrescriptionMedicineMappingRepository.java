package com.flipkart.pharma.prescriptionmanagement.repository;

import com.flipkart.pharma.prescriptionmanagement.domain.PrescriptionMedicineMapper;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by sourabh.d on 14/06/18.
 */
public interface PrescriptionMedicineMappingRepository extends CrudRepository<PrescriptionMedicineMapper, Long> {
    List<PrescriptionMedicineMapper> getByPid(String pid);
}
