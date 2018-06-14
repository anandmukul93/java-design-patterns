package com.flipkart.pharma.prescriptionmanagement.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.flipkart.pharma.prescriptionmanagement.common.MedicineType;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by sourabh.d on 14/06/18.
 */
@Getter
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrescriptionResponse {
    private String pid;
    private MedicineType medicineType;
    private String medicineName;
    private Integer quantity;
    private String remarks;
}
