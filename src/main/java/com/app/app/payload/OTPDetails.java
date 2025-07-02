package com.app.app.payload;

public class OTPDetails {
    private String otp;
    private long timestamp;

    public OTPDetails(String otp, long timestamp) {
        this.otp = otp;
        this.timestamp = timestamp;
    }

    public String getOtp() {
        return otp;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
