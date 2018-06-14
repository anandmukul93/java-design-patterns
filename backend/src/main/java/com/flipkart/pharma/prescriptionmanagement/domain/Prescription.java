package com.flipkart.pharma.prescriptionmanagement.domain;

import com.flipkart.pharma.prescriptionmanagement.common.PrescriptionType;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by sourabh.d on 14/06/18.
 */
@Data
@Entity
@Table(name = "prescription")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seqGen")
    @TableGenerator(name = "seqGen", allocationSize = 50)
    @Column(name = "id")
    private Long id;

    @Column(name = "pid")
    private String pid;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PrescriptionType type;

    @JoinColumn(name = "medicine_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Medicine medicine;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "remarks")
    private String remarks;


}
