package com.flipkart.pharma.prescriptionmanagement.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seqGen")
    @TableGenerator(name = "seqGen", allocationSize = 50)
    @Column(name = "id")
    private Long id;

    @Column(unique=true)
    private String DIN;

    private String name;

    private String phone;
}
