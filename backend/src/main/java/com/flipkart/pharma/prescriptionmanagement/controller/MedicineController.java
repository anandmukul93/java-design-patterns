package com.flipkart.pharma.prescriptionmanagement.controller;

import com.flipkart.pharma.prescriptionmanagement.domain.Medicine;
import com.flipkart.pharma.prescriptionmanagement.domain.Prescription;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreateMedicineRequest;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreatePrescriptionRequest;
import com.flipkart.pharma.prescriptionmanagement.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/medicine")
@Slf4j
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createMedicine(
            @RequestBody @Valid CreateMedicineRequest request) throws Exception {
        Medicine medicine = medicineService.create(request);
        return new ResponseEntity(medicine, HttpStatus.OK);
    }
}
