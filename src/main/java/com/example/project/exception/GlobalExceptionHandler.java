package com.example.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateMilestoneTitleException.class)
    public ResponseEntity<Object> handleDuplicateMilestoneTitle(DuplicateMilestoneTitleException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidDateRangeException.class)
    public ResponseEntity<Object> handleInvalidDateRange(InvalidDateRangeException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReleaseTagNotUniqueException.class)
    public ResponseEntity<Object> handleReleaseTagNotUnique(ReleaseTagNotUniqueException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ReleaseAlreadyAssociatedException.class)
    public ResponseEntity<Object> handleReleaseAlreadyAssociated(ReleaseAlreadyAssociatedException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MilestoneNotFoundException.class)
    public ResponseEntity<Object> handleMilestoneNotFound(MilestoneNotFoundException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReleaseNotFoundException.class)
    public ResponseEntity<Object> handleReleaseNotFound(ReleaseNotFoundException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> buildResponse(String message, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}
