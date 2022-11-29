package com.example.timetracker.exceptions;


public class EmailNotValidException extends RuntimeException {

    public EmailNotValidException(){
        super();
    }
    public EmailNotValidException( String message) {
        super(message);
    }
}