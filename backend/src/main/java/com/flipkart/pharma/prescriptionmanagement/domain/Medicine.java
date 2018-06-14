package com.flipkart.pharma.prescriptionmanagement.domain;

import com.flipkart.pharma.prescriptionmanagement.common.MedicineType;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by sourabh.d on 14/06/18.
 */
@Data
@Entity
@Table(name = "medicines")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seqGen")
    @TableGenerator(name = "seqGen", allocationSize = 50)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MedicineType type;

    @Column(name = "description")
    private String description;
}
