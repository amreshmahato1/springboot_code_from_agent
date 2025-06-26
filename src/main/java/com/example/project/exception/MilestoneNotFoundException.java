package com.example.project.exception;

public class MilestoneNotFoundException extends RuntimeException {
    public MilestoneNotFoundException(String message) {
        super(message);
    }

    public MilestoneNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
