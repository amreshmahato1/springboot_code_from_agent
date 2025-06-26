package com.example.project.exception;

public class ReleaseTagNotUniqueException extends RuntimeException {
    public ReleaseTagNotUniqueException(String message) {
        super(message);
    }

    public ReleaseTagNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }
}
