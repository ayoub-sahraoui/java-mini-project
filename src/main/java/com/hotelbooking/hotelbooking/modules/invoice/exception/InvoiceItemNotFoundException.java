package com.hotelbooking.hotelbooking.modules.invoice.exception;

public class InvoiceItemNotFoundException extends RuntimeException {

    public InvoiceItemNotFoundException(String message) {
        super(message);
    }
}
