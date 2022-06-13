package com.pharmacy.pharmacyweb.pharmacy;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String message) {
        super(message);
    }
}
