package com.flipkart.pharma.prescriptionmanagement.service.impl;

import com.flipkart.pharma.prescriptionmanagement.domain.Seller;
import com.flipkart.pharma.prescriptionmanagement.exception.PmaException;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreateSellerRequest;
import com.flipkart.pharma.prescriptionmanagement.repository.SellerRepository;
import com.flipkart.pharma.prescriptionmanagement.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sourabh.d on 14/06/18.
 */

@Component
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public Seller create(CreateSellerRequest request) throws PmaException {
        Seller seller = sellerRepository.findBySid(request.getSid());
        if(seller != null) {
            throw new PmaException("Seller with sid : " + request.getSid() + " already registered");
        }

        try {
            seller = new Seller();
            this.setDomainAttributes(seller, request);
            sellerRepository.save(seller);
            return seller;
        } catch (Exception e) {
            throw new PmaException("Error while adding new seller");
        }
    }

    private void setDomainAttributes(Seller seller, CreateSellerRequest request) {
        seller.setName(request.getName());
        seller.setAddress(request.getAddress());
        seller.setSid(request.getSid());
        seller.setPhoneNo(request.getPhoneNumber());
    }
}
