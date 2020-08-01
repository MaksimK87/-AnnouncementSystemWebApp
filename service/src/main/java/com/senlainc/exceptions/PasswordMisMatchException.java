package com.senlainc.exceptions;

public class PasswordMisMatchException extends RuntimeException {

    public PasswordMisMatchException() {
    }

    public PasswordMisMatchException(String message) {
        super(message);
    }
}
