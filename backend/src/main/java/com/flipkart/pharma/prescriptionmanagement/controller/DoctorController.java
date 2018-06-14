package com.flipkart.pharma.prescriptionmanagement.controller;

import com.flipkart.pharma.prescriptionmanagement.domain.Doctor;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreateDoctorRequest;
import com.flipkart.pharma.prescriptionmanagement.service.DoctorService;
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
@RequestMapping("/doctor")
@Slf4j
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createDoctor(@RequestBody @Valid CreateDoctorRequest request) throws Exception {
        Doctor doctor = doctorService.createDoctor(request);
        return new ResponseEntity(doctor, HttpStatus.OK);
    }

}
