package com.example.finalProject.exception;

public class PositionNotFoundException extends RuntimeException{
    public PositionNotFoundException(String message) {
        super(message);
    }
}
