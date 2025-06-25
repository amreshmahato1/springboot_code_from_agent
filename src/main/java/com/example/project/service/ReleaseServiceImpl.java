package com.example.project.service;

import com.example.project.dto.ReleaseResponseDto;
import com.example.project.entity.Milestone;
import com.example.project.entity.Release;
import com.example.project.exception.MilestoneNotFoundException;
import com.example.project.exception.ReleaseAlreadyAssociatedException;
import com.example.project.exception.ReleaseNotFoundException;
import com.example.project.exception.ReleaseTagNotUniqueException;
import com.example.project.repository.MilestoneRepository;
import com.example.project.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    private final ReleaseRepository releaseRepository;
    private final MilestoneRepository milestoneRepository;

    @Autowired
    public ReleaseServiceImpl(ReleaseRepository releaseRepository, MilestoneRepository milestoneRepository) {
        this.releaseRepository = releaseRepository;
        this.milestoneRepository = milestoneRepository;
    }

    @Override
    @Transactional
    public ReleaseResponseDto associateWithMilestone(Long releaseId, Long milestoneId) {
        Release release = releaseRepository.findById(releaseId)
                .orElseThrow(() -> new ReleaseNotFoundException("Release not found"));
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new MilestoneNotFoundException("Milestone not found"));
        if (release.getMilestone() != null) {
            throw new ReleaseAlreadyAssociatedException("Release is already associated with a milestone");
        }
        if (milestone.getRelease() != null) {
            throw new ReleaseAlreadyAssociatedException("Milestone is already associated with a release");
        }
        release.setMilestone(milestone);
        milestone.setRelease(release);
        releaseRepository.save(release);
        milestoneRepository.save(milestone);
        ReleaseResponseDto response = new ReleaseResponseDto();
        response.setId(release.getId());
        response.setTag(release.getTag());
        response.setMilestoneId(milestone.getId());
        return response;
    }
}
