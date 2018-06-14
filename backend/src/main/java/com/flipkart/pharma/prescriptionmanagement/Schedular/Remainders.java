package com.flipkart.pharma.prescriptionmanagement.Schedular;

import com.flipkart.pharma.prescriptionmanagement.domain.Prescription;
import com.flipkart.pharma.prescriptionmanagement.exception.PmaException;
import com.flipkart.pharma.prescriptionmanagement.model.response.PrescriptionResponse;
import com.flipkart.pharma.prescriptionmanagement.repository.ValidationRepository;
import com.flipkart.pharma.prescriptionmanagement.service.PrescriptionService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by sourabh.d on 14/06/18.
 */
@Component
public class Remainders {
    @Autowired
    private ValidationRepository validationRepository;

    @Autowired
    private PrescriptionService prescriptionService;

    //@Scheduled(cron = "0 0 12 * * ?")
    public void purchaseRemainder() {
        List<Prescription> prescriptions = validationRepository.getByIsPurchased(true);
        for(Prescription prescription : prescriptions) {
            System.out.println("Hello your prescription with pid : " + prescription.getPresciptionId() + " is not purchased");
        }
    }

    @Scheduled(fixedDelay = 300000)
    public void pillsRemainder() throws PmaException {
        List<Prescription> prescriptions = validationRepository.getByIsPurchased(true);
        for(Prescription prescription : prescriptions) {
           if(prescription.getToNotify() != null && prescription.getToNotify()) {
               List<PrescriptionResponse> prescriptionResponses = prescriptionService.getPrescription(prescription.getPresciptionId());
               StringBuilder message = new StringBuilder("Pills remainder : ");
               Boolean isAny = false;
               Date time = new Date(System.currentTimeMillis() - 3600 * 1000);
               for(PrescriptionResponse mapping : prescriptionResponses) {
                   Time pillTime  = mapping.getTime();
                   if(pillTime.after(time)) {
                       isAny = true;
                       message.append(" Medicine name : " + mapping.getMedicineName());
                   }
               }
               if(isAny) {
                   System.out.println(message.toString());
               }
           }
        }
    }
}
