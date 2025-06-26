package com.example.project.exception;

public class ReleaseAlreadyAssociatedException extends RuntimeException {
    public ReleaseAlreadyAssociatedException(String message) {
        super(message);
    }

    public ReleaseAlreadyAssociatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
