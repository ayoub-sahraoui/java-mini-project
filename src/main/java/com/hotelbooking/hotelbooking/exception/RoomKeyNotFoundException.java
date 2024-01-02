package com.hotelbooking.hotelbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RoomKeyNotFoundException extends RuntimeException {

    public RoomKeyNotFoundException(String message) {
        super(message);
    }

    public RoomKeyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
