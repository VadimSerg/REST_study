package com.example.restStudy.customsExceptions;

public class UserNotFoundException extends Exception   {

    private final String message;


    public String getMessage() {

        return message;
    }

    public UserNotFoundException(String message) {

        this.message=message;
    }
}
