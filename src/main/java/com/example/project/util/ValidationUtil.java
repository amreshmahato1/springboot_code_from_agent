package com.example.project.util;

import com.example.project.entity.Milestone;
import com.example.project.entity.Release;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidationUtil {
    public void validateDateRange(LocalDate startDate, LocalDate dueDate) {
        if (startDate.isAfter(dueDate)) {
            throw new com.example.project.exception.InvalidDateRangeException("Start date must be before or equal to due date.");
        }
    }

    public void validateMilestoneProjectOrGroup(Milestone milestone) {
        if ((milestone.getProjectId() == null && milestone.getGroupId() == null) ||
            (milestone.getProjectId() != null && milestone.getGroupId() != null)) {
            throw new IllegalArgumentException("Milestone must belong to either a project or a group, not both or neither.");
        }
    }

    public void validateReleaseNotAlreadyAssociated(Release release) {
        if (release.getMilestoneId() != null) {
            throw new com.example.project.exception.ReleaseAlreadyAssociatedException("Release is already associated with a milestone.");
        }
    }
}
