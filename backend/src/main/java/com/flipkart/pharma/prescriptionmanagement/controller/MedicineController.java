package com.flipkart.pharma.prescriptionmanagement.controller;

import com.flipkart.pharma.prescriptionmanagement.domain.Medicine;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreateMedicineRequest;
import com.flipkart.pharma.prescriptionmanagement.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getMedicine(@RequestParam(name = "name", required = false) String name) throws Exception {
        List<Medicine> medicineList = medicineService.search(name);
        return new ResponseEntity(medicineList, HttpStatus.OK);
    }
}
