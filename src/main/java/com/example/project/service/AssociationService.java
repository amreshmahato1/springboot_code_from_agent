package com.example.project.service;

import com.example.project.entity.Release;
import com.example.project.entity.Milestone;
import com.example.project.repository.ReleaseRepository;
import com.example.project.repository.MilestoneRepository;
import com.example.project.exception.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AssociationService {
    private final ReleaseRepository releaseRepository;
    private final MilestoneRepository milestoneRepository;

    @Autowired
    public AssociationService(ReleaseRepository releaseRepository, MilestoneRepository milestoneRepository) {
        this.releaseRepository = releaseRepository;
        this.milestoneRepository = milestoneRepository;
    }

    public Release linkReleaseToMilestone(String releaseId, String milestoneId) {
        Release release = releaseRepository.findById(releaseId)
                .orElseThrow(() -> new ValidationException("Release not found"));
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new ValidationException("Milestone not found"));
        release.setMilestoneId(milestone.getId());
        return releaseRepository.save(release);
    }
}
