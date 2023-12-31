package com.hotelbooking.hotelbooking.exception;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(int id){
        super("Could not found this User !");
    }
    public UserNotFoundException(String email){
        super("Could not found User with this Email !");
    }
}
