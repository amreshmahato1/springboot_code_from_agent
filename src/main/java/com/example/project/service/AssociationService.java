package com.example.project.service;

import com.example.project.dto.ReleaseMilestoneAssociationResponse;
import com.example.project.entity.Milestone;
import com.example.project.entity.Release;
import com.example.project.exception.MilestoneNotFoundException;
import com.example.project.exception.ReleaseAlreadyAssociatedException;
import com.example.project.exception.ReleaseNotFoundException;
import com.example.project.repository.MilestoneRepository;
import com.example.project.repository.ReleaseRepository;
import com.example.project.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssociationService {
    private final ReleaseRepository releaseRepository;
    private final MilestoneRepository milestoneRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public AssociationService(ReleaseRepository releaseRepository, MilestoneRepository milestoneRepository, ValidationUtil validationUtil) {
        this.releaseRepository = releaseRepository;
        this.milestoneRepository = milestoneRepository;
        this.validationUtil = validationUtil;
    }

    @Transactional
    public ReleaseMilestoneAssociationResponse associateReleaseWithMilestone(Long releaseId, Long milestoneId) {
        Release release = releaseRepository.findById(releaseId)
                .orElseThrow(() -> new ReleaseNotFoundException("Release not found: " + releaseId));
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new MilestoneNotFoundException("Milestone not found: " + milestoneId));

        // Validate release is not already associated
        validationUtil.validateReleaseNotAlreadyAssociated(release);

        // Associate and save
        release.setMilestoneId(milestone.getId());
        releaseRepository.save(release);

        return new ReleaseMilestoneAssociationResponse(release.getId(), milestone.getId(), "Release associated with milestone successfully.");
    }
}
