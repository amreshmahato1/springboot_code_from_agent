package com.example.project.utility;

import com.example.project.entity.Milestone;
import com.example.project.entity.Release;
import com.example.project.exception.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class ValidationUtil {
    public void validateMilestoneTitleUnique(Optional<Milestone> milestoneOpt, String title) {
        if (milestoneOpt.isPresent()) {
            throw new DuplicateMilestoneTitleException("Milestone title '" + title + "' already exists in the project or group.");
        }
    }

    public void validateDateRange(LocalDate startDate, LocalDate dueDate) {
        if (startDate.isAfter(dueDate)) {
            throw new InvalidDateRangeException("Start date must be before or equal to due date.");
        }
    }

    public void validateReleaseTagUnique(Optional<Release> releaseOpt, String tag) {
        if (releaseOpt.isPresent()) {
            throw new ReleaseTagNotUniqueException("Release tag '" + tag + "' already exists in the project.");
        }
    }

    public void validateReleaseNotAlreadyAssociated(Release release) {
        if (release.getMilestoneId() != null) {
            throw new ReleaseAlreadyAssociatedException("Release is already associated with a milestone.");
        }
    }
}