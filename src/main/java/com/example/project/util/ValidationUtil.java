package com.example.project.util;

import com.example.project.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidationUtil {
    public void validateDueDate(LocalDate dueDate) {
        if (dueDate.isBefore(LocalDate.now())) {
            throw new ValidationException("Due date must be today or in the future.");
        }
    }

    public void validateReleaseDate(LocalDate releaseDate) {
        if (releaseDate.isBefore(LocalDate.now())) {
            throw new ValidationException("Release date must be today or in the future.");
        }
    }
}
