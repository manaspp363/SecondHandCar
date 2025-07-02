package com.app.app.service;

import com.app.app.payload.OTPDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class OTPService {
    private OTPDetails otpDetails;
    private final Map<String, OTPDetails> otpStore = new HashMap<>();
    public static final int OTP_EXPARATION_TIME = 5;

    public String genrateOTP(String mobile) {
        String otp = String.valueOf((int) (Math.random() * 899999) + 100000);
        OTPDetails otpDetails = new OTPDetails(otp, System.currentTimeMillis());
        otpStore.put(mobile, otpDetails);
        return otp;
    }

    public boolean validateOTP(String mobile, String otp) {
        OTPDetails otpDetails = otpStore.get(mobile);

        if (otpDetails == null) {
            return false;

        }

        long currentTime = System.currentTimeMillis();
        long otpTime = otpDetails.getTimestamp();
        long timeDifference = TimeUnit.MILLISECONDS.toMinutes(currentTime - otpTime);
        if (timeDifference > OTP_EXPARATION_TIME) {
            return false;

        }
        return otpDetails.getOtp().equals(otp);

    }
}