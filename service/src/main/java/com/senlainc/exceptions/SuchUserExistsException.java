package com.senlainc.exceptions;


public class SuchUserExistsException extends RuntimeException {

    public SuchUserExistsException() {
    }

    public SuchUserExistsException(String message) {
        super(message);
    }
}
