package com.flipkart.pharma.prescriptionmanagement.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.flipkart.pharma.prescriptionmanagement.common.MedicineType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Time;

/**
 * Created by sourabh.d on 14/06/18.
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePrMedicineMappingRequest {
    @NotNull
    private String pid;

    @NotNull
    private Long medicineId;

    private Integer quantity;

    private String remarks;

    private Integer noOfDays;

    private Time time;

    private Boolean toNotify;
}
