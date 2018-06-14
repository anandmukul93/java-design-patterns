package com.flipkart.pharma.prescriptionmanagement.domain;

import com.flipkart.pharma.prescriptionmanagement.common.MedicineType;
import lombok.Data;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by sourabh.d on 14/06/18.
 */
@Data
@Entity
@Table(name = "prescription_medicine_mapping")
public class PrescriptionMedicineMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seqGen")
    @TableGenerator(name = "seqGen", allocationSize = 50)
    @Column(name = "id")
    private Long id;

    @Column(name = "pid")
    private String pid;

    @JoinColumn(name = "medicine_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Medicine medicine;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "number_of_days")
    private Integer noOfDays;

    @Column(name = "time")
    private Time time;
}
