package com.example.timetracker.exceptions;

public class InvalidRequestException extends  RuntimeException{
    public InvalidRequestException ( String message) {
        super(message);
    }
}
