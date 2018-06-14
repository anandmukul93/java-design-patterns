package com.flipkart.pharma.prescriptionmanagement.repository;

import com.flipkart.pharma.prescriptionmanagement.domain.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    @Query(value = "select d from Doctor d where d.DIN = :din")
    Doctor searchByDIN(@Param("din") String din);
}
