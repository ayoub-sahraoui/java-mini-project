package com.hotelbooking.hotelbooking.modules.employee.exception;

public class GuestNotFoundException extends RuntimeException {

    public GuestNotFoundException(String message) {
        super(message);
    }
}
