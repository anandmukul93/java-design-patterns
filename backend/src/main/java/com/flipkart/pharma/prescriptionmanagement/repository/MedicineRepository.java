package com.flipkart.pharma.prescriptionmanagement.repository;

import com.flipkart.pharma.prescriptionmanagement.domain.Medicine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sourabh.d on 14/06/18.
 */
public interface MedicineRepository extends CrudRepository<Medicine, Long> {
    @Query(value = "select m from Medicine m where m.name like %:name%")
    List<Medicine> searchByName(@Param("name") String name);
}
