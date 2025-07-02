package com.app.app.service;

import com.app.app.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final TwilioConfig twilioConfig;
    public SmsService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }



    public String sendSms(String to, String messageBody) {
        Message message = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(twilioConfig.getPhoneNumber()),
                messageBody
        ).create();

        return "Message sent with SID: " + message.getSid();
    }
}
