package com.flipkart.pharma.prescriptionmanagement.client;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSClient {
    public static final String ACCOUNT_SID = "AC00141fd1d338ca2413c06da04f117646";
    public static final String AUTH_TOKEN = "d7084025703756d15124cc8f58255293";
    public static final String FROM_PHONE_NO = "+17865902319";

    public static String sendSMS(String smsContent, String toNumber){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber(toNumber),
                new PhoneNumber(FROM_PHONE_NO),
                smsContent).create();

        return message.getSid();
    }
}
