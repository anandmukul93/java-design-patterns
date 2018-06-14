package com.flipkart.pharma.prescriptionmanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "otp")
public class OTP {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "otp_no")
    private String OTP;

    @Column(name = "prescription_id")
    private String prescriptionId;

    @Column(name = "expiration_time")
    private Date epxiration_time;

    @Column(name = "is_valid")
    private Boolean isValid;
}
