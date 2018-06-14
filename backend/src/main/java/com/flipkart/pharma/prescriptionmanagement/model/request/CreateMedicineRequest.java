package com.flipkart.pharma.prescriptionmanagement.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.flipkart.pharma.prescriptionmanagement.common.MedicineType;
import lombok.Data;

/**
 * Created by sourabh.d on 14/06/18.
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateMedicineRequest {
    private String medicineName;

    private String description;

    private MedicineType type = MedicineType.MEDICINE;
}
