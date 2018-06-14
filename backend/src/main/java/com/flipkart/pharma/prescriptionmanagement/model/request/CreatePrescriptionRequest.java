package com.flipkart.pharma.prescriptionmanagement.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.flipkart.pharma.prescriptionmanagement.common.PrescriptionType;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by sourabh.d on 14/06/18.
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePrescriptionRequest {
    @NotNull
    private String pid;

    private PrescriptionType type = PrescriptionType.MEDICINE;

    @NotNull
    private Long medicineId;

    private Integer quantity;

    private String remarks;
}
