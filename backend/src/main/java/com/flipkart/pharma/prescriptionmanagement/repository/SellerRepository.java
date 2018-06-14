package com.flipkart.pharma.prescriptionmanagement.repository;

import com.flipkart.pharma.prescriptionmanagement.domain.Seller;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sourabh.d on 14/06/18.
 */
public interface SellerRepository extends CrudRepository<Seller, Long> {
    Seller findBySid(String sid);
}
