package com.flipkart.pharma.prescriptionmanagement.Schedular;

import com.flipkart.pharma.prescriptionmanagement.domain.Prescription;
import com.flipkart.pharma.prescriptionmanagement.repository.ValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sourabh.d on 14/06/18.
 */
@Component
public class Remainders {
    @Autowired
    private ValidationRepository validationRepository;

    @Scheduled(cron = "0 0 12 * * ?")
    public void purchaseRemainder() {
        List<Prescription> prescriptions = validationRepository.getByIsPurchased(true);
        for(Prescription prescription : prescriptions) {
            System.out.println("Hello your prescription with pid : " + prescription.getPresciptionId() + " is not purchased");
        }
    }

    public void pillsRemainder() {

    }
}
