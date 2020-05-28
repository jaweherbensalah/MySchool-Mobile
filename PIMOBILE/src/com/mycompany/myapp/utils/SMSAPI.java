/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;

import static com.codename1.ui.events.ActionEvent.Type.Response;
import java.util.Map;
import com.codename1.components.ToastBar;
import  com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.util.Base64;
import java.util.Map;


/**
 *
 * @author LENOVO
 */
public class SMSAPI {
     String accountSID = "ACa7cb8cb8295a4f53968272becfc0c706";
    String authToken = "919a0bddba5cb4e5ccde73d0297813dd";
    String fromPhone = "+14155238886";
    
    public SMSAPI(String message, String to){
        
        Response <Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
        queryParam("To", to).
        queryParam("From", fromPhone).
        queryParam("Body", message).
        header("Authorization", "Basic " + Base64.encodeNoNewline((accountSID + ":" + authToken).getBytes())).
        getAsJsonMap();
        System.out.println(result.getResponseData());
        
        
        if(result.getResponseData() != null) {
            String error = (String)result.getResponseData().get("error_message");
        if(error != null) {
            System.out.println(error);
        }
        } else {
            //ToastBar.showErrorMessage("Error sending SMS: " + result.getResponseCode());
        }
        }
    
}
