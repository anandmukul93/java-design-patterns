package com.flipkart.pharma.prescriptionmanagement.Schedular;

import com.flipkart.pharma.prescriptionmanagement.client.SMSClient;
import com.flipkart.pharma.prescriptionmanagement.domain.Prescription;
import com.flipkart.pharma.prescriptionmanagement.domain.PrescriptionMedicineMapper;
import com.flipkart.pharma.prescriptionmanagement.repository.PrescriptionMedicineMappingRepository;
import com.flipkart.pharma.prescriptionmanagement.repository.ValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.SimpleDateFormat;
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

    @Scheduled(fixedDelay = 300000)
    public void purchaseRemainder() {
        List<Prescription> prescriptions = validationRepository.getByIsPurchased(true);
        for(Prescription prescription : prescriptions) {
            Date currentDate = new Date(System.currentTimeMillis());
            if(prescription.getExpiry().after(currentDate)) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
                String strDate = formatter.format(prescription.getExpiry());
                String[] date = strDate.split(" ");
                String message = "Hi,+your+prescription+with+id+" + prescription.getPrescriptionId() + "+will+expire+on+" + date[0] + "+" + date[1] + "+" + date[2];
                System.out.println(message);
                SMSClient.sendSMS(message, prescription.getIssuedPhoneNo());
            }
        }
    }

    @Scheduled(fixedDelay = 300000)
    @Transactional
    public void pillsRemainder() throws Exception {
        List<Prescription> prescriptions = validationRepository.getByIsPurchased(true);
        for(Prescription prescription : prescriptions) {
           if(prescription.getToNotify() != null && prescription.getToNotify()) {
               List<PrescriptionMedicineMapper> prescriptionResponses = prescriptionRepository.getByPid(prescription.getPrescriptionId());
               StringBuilder message = new StringBuilder("Pills+remainder:");
               Boolean isAny = false;
               Date time = new Date(System.currentTimeMillis() - 3600 * 1000);
               for(PrescriptionMedicineMapper mapping : prescriptionResponses) {
                   Time pillTime  = mapping.getTime();
                   Time time1 = new Time(time.getTime());
                   if(pillTime.before(time1)) {
                       if(isAny)
                           message.append(",");
                       isAny = true;
                       message.append("Medicine+name:" + mapping.getMedicine().getName());
                   }
               }
               if(isAny) {
                   //SMSClient.sendSMS(message.toString(), prescription.getIssuedPhoneNo());
                   System.out.println(message.toString());
               }
           }
        }
    }
}
