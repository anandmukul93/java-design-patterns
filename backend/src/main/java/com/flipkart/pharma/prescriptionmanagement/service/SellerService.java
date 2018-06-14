package com.flipkart.pharma.prescriptionmanagement.service;

import com.flipkart.pharma.prescriptionmanagement.domain.Seller;
import com.flipkart.pharma.prescriptionmanagement.exception.PmaException;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreateSellerRequest;

/**
 * Created by sourabh.d on 14/06/18.
 */
public interface SellerService {
    Seller create(CreateSellerRequest request) throws PmaException;
}
