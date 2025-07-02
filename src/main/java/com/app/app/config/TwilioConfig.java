package com.app.app.config;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.print.DocFlavor;


@Configuration
public class TwilioConfig {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String phoneNumber;

    @Bean
    public String init(){
        Twilio.init(accountSid, authToken);
        return "Method intitialized";
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

