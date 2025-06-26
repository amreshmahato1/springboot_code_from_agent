package com.example.project.util;

import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.regex.Pattern;

@Component
public class ValidationUtil {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z0-9 _-]{3,50}$");

    public boolean isValidMilestoneName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

    public boolean isValidMilestoneDates(LocalDate startDate, LocalDate endDate) {
        return startDate != null && endDate != null && endDate.isAfter(startDate);
    }

    public boolean isValidReleaseName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

    public boolean isValidReleaseDates(LocalDate startDate, LocalDate endDate) {
        return startDate != null && endDate != null && endDate.isAfter(startDate);
    }

    // Add more reusable validation methods as needed
}
