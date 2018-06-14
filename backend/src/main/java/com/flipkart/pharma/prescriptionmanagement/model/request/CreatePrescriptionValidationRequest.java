package com.flipkart.pharma.prescriptionmanagement.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePrescriptionValidationRequest {
    private String docIdNo;
    private String patientPhoneNo;
    private String patientEmail;
    private Boolean toNotify;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expiry;
    private List<CreatePrMedicineMappingRequest> medicines;
}
