package com.example.finalProject.exception;

public class PortfolioNotFoundException extends RuntimeException{
    public PortfolioNotFoundException(String message) {
        super(message);
    }
}
