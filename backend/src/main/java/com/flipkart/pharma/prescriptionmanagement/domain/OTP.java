package com.flipkart.pharma.prescriptionmanagement.domain;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.format.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date epxirationTime;

    @Column(name = "is_valid")
    private Boolean isValid;
}
