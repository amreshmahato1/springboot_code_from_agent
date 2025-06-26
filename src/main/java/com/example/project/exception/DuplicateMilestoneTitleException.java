package com.example.project.exception;

public class DuplicateMilestoneTitleException extends RuntimeException {
    public DuplicateMilestoneTitleException(String message) {
        super(message);
    }

    public DuplicateMilestoneTitleException(String message, Throwable cause) {
        super(message, cause);
    }
}
