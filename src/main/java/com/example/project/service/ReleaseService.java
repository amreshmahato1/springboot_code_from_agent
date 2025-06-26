package com.example.project.service;

import com.example.project.entity.Release;
import com.example.project.entity.Milestone;
import com.example.project.repository.ReleaseRepository;
import com.example.project.repository.MilestoneRepository;
import com.example.project.exception.ValidationException;
import com.example.project.util.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ReleaseService {
    private final ReleaseRepository releaseRepository;
    private final MilestoneRepository milestoneRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public ReleaseService(ReleaseRepository releaseRepository, MilestoneRepository milestoneRepository, ValidationUtil validationUtil) {
        this.releaseRepository = releaseRepository;
        this.milestoneRepository = milestoneRepository;
        this.validationUtil = validationUtil;
    }

    public Release associateWithMilestone(String releaseId, String milestoneId) {
        Release release = releaseRepository.findById(releaseId)
                .orElseThrow(() -> new ValidationException("Release not found"));
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new ValidationException("Milestone not found"));
        release.setMilestoneId(milestone.getId());
        return releaseRepository.save(release);
    }

    public void validateRelease(Release release) {
        if (!validationUtil.isValidReleaseName(release.getName())) {
            throw new ValidationException("Invalid release name");
        }
        if (!validationUtil.isValidReleaseDates(release.getStartDate(), release.getEndDate())) {
            throw new ValidationException("Release end date must be after start date");
        }
        // Add more validation as per LLD
    }
}
