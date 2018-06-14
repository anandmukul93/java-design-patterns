package com.flipkart.pharma.prescriptionmanagement.controller;

import com.flipkart.pharma.prescriptionmanagement.domain.Seller;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreateSellerRequest;
import com.flipkart.pharma.prescriptionmanagement.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/seller")
@Slf4j
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createSeller(
            @RequestBody @Valid CreateSellerRequest request) throws Exception {
        Seller seller = sellerService.create(request);
        return new ResponseEntity(seller, HttpStatus.OK);
    }

    @RequestMapping(value = "/{pid}/purchased", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updatePurchase(@PathVariable("pid") String pid) throws Exception {
        sellerService.updatePurchase(pid);
        return new ResponseEntity(HttpStatus.OK);
    }
}
