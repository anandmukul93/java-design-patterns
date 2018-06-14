package com.flipkart.pharma.prescriptionmanagement.Schedular;

import com.flipkart.pharma.prescriptionmanagement.domain.Prescription;
import com.flipkart.pharma.prescriptionmanagement.domain.PrescriptionMedicineMapper;
import com.flipkart.pharma.prescriptionmanagement.exception.PmaException;
import com.flipkart.pharma.prescriptionmanagement.repository.PrescriptionMedicineMappingRepository;
import com.flipkart.pharma.prescriptionmanagement.repository.ValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by sourabh.d on 14/06/18.
 */
@Component
@EnableTransactionManagement
public class Remainders {
    @Autowired
    private ValidationRepository validationRepository;

    @Autowired
    private PrescriptionMedicineMappingRepository prescriptionRepository;

    @Scheduled(cron = "0 0 12 * * ?")
    public void purchaseRemainder() {
        List<Prescription> prescriptions = validationRepository.getByIsPurchased(true);
        for(Prescription prescription : prescriptions) {
            System.out.println("Hello your prescription with pid : " + prescription.getPresciptionId() + " is not purchased");
        }
    }

    @Scheduled(fixedDelay = 300000)
    @Transactional
    public void pillsRemainder() throws PmaException {
        List<Prescription> prescriptions = validationRepository.getByIsPurchased(true);
        for(Prescription prescription : prescriptions) {
           if(prescription.getToNotify() != null && prescription.getToNotify()) {
               List<PrescriptionMedicineMapper> prescriptionResponses = prescriptionRepository.getByPid(prescription.getPresciptionId());
               StringBuilder message = new StringBuilder("Pills remainder : ");
               Boolean isAny = false;
               Date time = new Date(System.currentTimeMillis() - 3600 * 1000);
               for(PrescriptionMedicineMapper mapping : prescriptionResponses) {
                   Time pillTime  = mapping.getTime();
                   Time time1 = new Time(time.getTime());
                   Date pillDate = new Date(System.currentTimeMillis());
                   if(pillTime.before(time1)) {
                       isAny = true;
                       message.append(" Medicine name : " + mapping.getMedicine().getName());
                   }
               }
               if(isAny) {
                   System.out.println(message.toString());
               }
           }
        }
    }
}
