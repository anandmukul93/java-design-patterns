package com.flipkart.pharma.prescriptionmanagement.model.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.flipkart.pharma.prescriptionmanagement.common.Status;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CheckValidationResponse {
    Status status;
    String prescriptionId;
}
