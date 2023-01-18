package com.example.restStudy.customsExceptions;

public class UserNotFoundException extends Exception   {

    private int statusCode;
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message, int statusCode) {
        this.message=message;
        this.statusCode = statusCode;

    }

    public UserNotFoundException(String message) { this.message=message;
    }
}
