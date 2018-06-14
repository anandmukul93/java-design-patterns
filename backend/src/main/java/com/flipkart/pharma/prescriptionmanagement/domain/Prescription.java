package com.flipkart.pharma.prescriptionmanagement.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "issued_email")
    private String issuedEmail;

    @Column(name = "is_purchased")
    private Boolean isPurchased;

    @Column(name = "to_notify")
    private Boolean toNotify;

    @Column(name = "expiry")
    private Date expiry;
}
