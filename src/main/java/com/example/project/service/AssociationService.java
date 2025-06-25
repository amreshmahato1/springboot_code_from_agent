package com.example.project.service;

import com.example.project.dto.ReleaseAssociationResponseDTO;
import com.example.project.entity.Milestone;
import com.example.project.entity.Release;
import com.example.project.exception.ReleaseAlreadyAssociatedException;
import com.example.project.repository.ReleaseRepository;
import com.example.project.service.MilestoneService;
import com.example.project.service.ReleaseService;
import com.example.project.utility.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssociationService {
    private final ReleaseRepository releaseRepository;
    private final MilestoneService milestoneService;
    private final ReleaseService releaseService;
    private final ValidationUtil validationUtil;

    @Autowired
    public AssociationService(ReleaseRepository releaseRepository, MilestoneService milestoneService, ReleaseService releaseService, ValidationUtil validationUtil) {
        this.releaseRepository = releaseRepository;
        this.milestoneService = milestoneService;
        this.releaseService = releaseService;
        this.validationUtil = validationUtil;
    }

    @Transactional
    public ReleaseAssociationResponseDTO associateReleaseWithMilestone(Long releaseId, Long milestoneId) {
        Release release = releaseService.findReleaseOrThrow(releaseId);
        Milestone milestone = milestoneService.findMilestoneOrThrow(milestoneId);
        validationUtil.validateReleaseNotAlreadyAssociated(release);
        release.setMilestoneId(milestone.getId());
        releaseRepository.save(release);
        return new ReleaseAssociationResponseDTO(release.getId(), milestone.getId(), "Release associated with milestone successfully.");
    }
}