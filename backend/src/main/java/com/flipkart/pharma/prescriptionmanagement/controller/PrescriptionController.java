package com.flipkart.pharma.prescriptionmanagement.controller;

import com.flipkart.pharma.prescriptionmanagement.domain.PrescriptionMedicineMapper;
import com.flipkart.pharma.prescriptionmanagement.model.request.CreatePrMedicineMappingRequest;
import com.flipkart.pharma.prescriptionmanagement.model.response.PrescriptionResponse;
import com.flipkart.pharma.prescriptionmanagement.service.PrescriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/prescription")
@Slf4j
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPrescriptionMedicineMapping(
            @RequestBody @Valid List<CreatePrMedicineMappingRequest> request,
            @RequestParam(name = "pid", required = false) String pid) throws Exception {
        List<PrescriptionMedicineMapper> prescriptions = prescriptionService.create(request, pid);
        return new ResponseEntity(prescriptions, HttpStatus.OK);
    }

    @RequestMapping(value = "/{pid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPrescriptionDetails(@PathVariable("pid") String pid) throws Exception{
        List<PrescriptionResponse> responseList = prescriptionService.getPrescription(pid);
        return new ResponseEntity(responseList, HttpStatus.OK);
    }
}
