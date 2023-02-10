package com.example.restStudy.customsExceptions;

public class UserNotFoundException extends Exception {

    private final String message;


    public UserNotFoundException(String message) {

        this.message = message;
    }

    public String getMessage() {

        return message;
    }
}
