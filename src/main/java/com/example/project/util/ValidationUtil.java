package com.example.project.util;

import java.time.LocalDate;

public class ValidationUtil {
    private ValidationUtil() {}

    public static boolean isValidDateRange(LocalDate start, LocalDate end) {
        return start != null && end != null && start.isBefore(end);
    }
}
