package com.example.project.service;

import com.example.project.dto.AssociationRequestDTO;
import com.example.project.entity.Milestone;
import com.example.project.entity.Release;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.repository.MilestoneRepository;
import com.example.project.repository.ReleaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AssociationService {
    private final ReleaseRepository releaseRepository;
    private final MilestoneRepository milestoneRepository;

    @Transactional
    public Release associateReleaseToMilestone(AssociationRequestDTO dto) {
        Release release = releaseRepository.findById(dto.getReleaseId())
                .orElseThrow(() -> new ResourceNotFoundException("Release not found with id: " + dto.getReleaseId()));
        Milestone milestone = milestoneRepository.findById(dto.getMilestoneId())
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found with id: " + dto.getMilestoneId()));
        release.setMilestone(milestone);
        return releaseRepository.save(release);
    }
}
