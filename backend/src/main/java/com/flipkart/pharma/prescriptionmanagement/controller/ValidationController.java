package com.flipkart.pharma.prescriptionmanagement.controller;


import com.flipkart.pharma.prescriptionmanagement.exception.PmaException;
import com.flipkart.pharma.prescriptionmanagement.model.request.CheckValidationRequest;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreatePrescriptionValidationRequest;
import com.flipkart.pharma.prescriptionmanagement.model.request.InitiateValidationRequest;
import com.flipkart.pharma.prescriptionmanagement.model.response.CheckValidationResponse;
import com.flipkart.pharma.prescriptionmanagement.model.response.CreatePrescriptionValidationResponse;
import com.flipkart.pharma.prescriptionmanagement.model.response.InitiateValidationResponse;
import com.flipkart.pharma.prescriptionmanagement.service.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/validation")
@Slf4j
public class ValidationController {

    @Autowired
    private ValidationService validationService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public CreatePrescriptionValidationResponse create(@RequestBody @Valid CreatePrescriptionValidationRequest createPrescriptionValidationRequest)throws Exception {
        return validationService.createPrescriptionValidationRecord(createPrescriptionValidationRequest);
    }

    @RequestMapping(value = "/initiate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public InitiateValidationResponse initiate(@RequestBody @Valid InitiateValidationRequest initiateValidationRequest)throws PmaException {
        return validationService.initiateValidation(initiateValidationRequest);
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces =  MediaType.APPLICATION_JSON)
    public CheckValidationResponse validate(@RequestBody @Valid CheckValidationRequest checkValidationRequest) throws PmaException {
        return validationService.checkValidation(checkValidationRequest);
    }

}
