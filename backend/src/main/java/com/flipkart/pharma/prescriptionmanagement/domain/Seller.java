package com.flipkart.pharma.prescriptionmanagement.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by sourabh.d on 14/06/18.
 */
@Data
@Entity
@Table(name = "seller")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seqGen")
    @TableGenerator(name = "seqGen", allocationSize = 50)
    @Column(name = "id")
    private Long id;

    @Column(name = "sid", nullable = false)
    private String sid;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNo;

    @Column(name = "address")
    private String address;
}
