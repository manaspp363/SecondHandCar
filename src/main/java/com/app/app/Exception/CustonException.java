package com.app.app.Exception;

class CustomException extends Exception {
    public CustomException(String message) {
        super(message);

    }
    public String NoEntityPresent(String message) {
        return message;
    }
}
