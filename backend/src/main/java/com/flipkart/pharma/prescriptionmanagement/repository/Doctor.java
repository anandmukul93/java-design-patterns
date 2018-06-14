package com.flipkart.pharma.prescriptionmanagement.repository;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Doctor {
    private String DIN;
    private String name;
    private String phone;
}
