package com.flipkart.pharma.prescriptionmanagement.datatypes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorRequest {
    private String name;
    private String DIN;
    private String phone;
}
