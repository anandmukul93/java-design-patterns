package com.flipkart.pharma.prescriptionmanagement.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Data
@Table(name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, name = "prescription_id")
    private String prescriptionId;

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

    @Column(name = "purchase_count")
    private Integer purchaseCount;

    @Column(name = "max_purchase")
    private Integer maxPurchase;

    @Column(name = "expiry")
    private Date expiry;
}
