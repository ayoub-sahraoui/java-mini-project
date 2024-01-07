package com.hotelbooking.hotelbooking.modules.room.exception;

public class RoomNotFoundException extends RuntimeException {

    public RoomNotFoundException(String message) {
        super(message);
    }
}
