package com.flipkart.pharma.prescriptionmanagement.common;


import java.util.Random;

public class Utils {

    public static enum StringType{
        OTP, PID;
    }

    public static String uniqueString(long length, StringType stringType) {
            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            String OTPCHARS = "1234567890";
            StringBuilder str = new StringBuilder();
            Random rnd = new Random();
            String charPool =  stringType == StringType.OTP ? OTPCHARS : SALTCHARS;
            while (str.length() < length) { // length of the random string.
                int index = (int) (rnd.nextFloat() * charPool.length());
                str.append(charPool.charAt(index));
            }
            return str.toString();
    }
}
