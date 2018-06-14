package com.flipkart.pharma.prescriptionmanagement.controller;

import com.flipkart.pharma.prescriptionmanagement.domain.Prescription;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreatePrescriptionRequest;
import com.flipkart.pharma.prescriptionmanagement.service.PrescriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/prescription")
@Slf4j
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPrescription(
            @RequestBody @Valid CreatePrescriptionRequest request) throws Exception {
        Prescription prescription = prescriptionService.create(request);
        return new ResponseEntity(prescription, HttpStatus.OK);
    }

  /*  @RequestMapping(value = "/{pid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPrescription(@PathVariable("pid") String pid) {

    }*/
}
