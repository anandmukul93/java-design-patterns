package com.flipkart.pharma.prescriptionmanagement.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Data
@Table(name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, name = "prescription_id")
    private String presciptionId;

    @Column(name = "doc_id_no")
    private String docIdNo;

    @Column(name = "issued_phone_no")
    private String issuedPhoneNo;

    @Email(message = "Email should be valid format")
    @Column(name = "issued_email")
    private String issuedEmail;
}
