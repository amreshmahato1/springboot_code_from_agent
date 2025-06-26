package com.example.project.exception;

public class ReleaseTagNotUniqueException extends RuntimeException {
    public ReleaseTagNotUniqueException(String message) {
        super(message);
    }
}
