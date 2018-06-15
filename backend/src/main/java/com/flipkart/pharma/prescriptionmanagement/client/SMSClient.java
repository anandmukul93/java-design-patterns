package com.flipkart.pharma.prescriptionmanagement.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SMSClient {
    public static final String ACCOUNT_SID = "AC00141fd1d338ca2413c06da04f117646";
    public static final String AUTH_TOKEN = "d7084025703756d15124cc8f58255293";
    public static final String FROM_PHONE_NO = "+17865902319";
    public static final String AUTH_KEY = "d6c949dd278992abbbf0eeef89798bb1";

    public static String sendSMS(String smsContent, String toNumber) {
        String url = "http://sms.bulksmsserviceproviders.com/api/send_http.php?authkey="+AUTH_KEY+"&mobiles="+toNumber+"&message="+smsContent+"&sender=MBBVPS&route=B";
        sendGet(url);
        return "sms sent";
    }

    private static void sendGet(String url) {

        URL obj = null;
        try {
            obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
