package com.example.milestonerelease.service;

import com.example.milestonerelease.dto.MilestoneReleaseAssociationDTO;
import com.example.milestonerelease.entity.Milestone;
import com.example.milestonerelease.entity.MilestoneReleaseAssociation;
import com.example.milestonerelease.entity.Release;
import com.example.milestonerelease.exception.BusinessValidationException;
import com.example.milestonerelease.exception.DuplicateResourceException;
import com.example.milestonerelease.exception.ResourceNotFoundException;
import com.example.milestonerelease.repository.MilestoneReleaseAssociationRepository;
import com.example.milestonerelease.repository.MilestoneRepository;
import com.example.milestonerelease.repository.ReleaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MilestoneReleaseAssociationService {
    private final MilestoneReleaseAssociationRepository associationRepository;
    private final MilestoneRepository milestoneRepository;
    private final ReleaseRepository releaseRepository;

    @Transactional
    public MilestoneReleaseAssociationDTO associate(Long milestoneId, Long releaseId) {
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found with id: " + milestoneId));
        Release release = releaseRepository.findById(releaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Release not found with id: " + releaseId));
        if (associationRepository.existsByMilestoneAndRelease(milestone, release)) {
            throw new DuplicateResourceException("Association already exists between milestone and release.");
        }
        if (milestone.isReleased()) {
            throw new BusinessValidationException("Cannot associate a released milestone.");
        }
        MilestoneReleaseAssociation association = MilestoneReleaseAssociation.builder()
                .milestone(milestone)
                .release(release)
                .associatedAt(LocalDateTime.now())
                .build();
        MilestoneReleaseAssociation saved = associationRepository.save(association);
        return toDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<MilestoneReleaseAssociationDTO> getAssociationsByMilestone(Long milestoneId) {
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found with id: " + milestoneId));
        return associationRepository.findByMilestone(milestone)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MilestoneReleaseAssociationDTO> getAssociationsByRelease(Long releaseId) {
        Release release = releaseRepository.findById(releaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Release not found with id: " + releaseId));
        return associationRepository.findByRelease(release)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public void removeAssociation(Long milestoneId, Long releaseId) {
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found with id: " + milestoneId));
        Release release = releaseRepository.findById(releaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Release not found with id: " + releaseId));
        MilestoneReleaseAssociation association = associationRepository.findByMilestoneAndRelease(milestone, release)
                .orElseThrow(() -> new ResourceNotFoundException("Association not found for given milestone and release."));
        associationRepository.delete(association);
    }

    public MilestoneReleaseAssociationDTO toDTO(MilestoneReleaseAssociation entity) {
        return MilestoneReleaseAssociationDTO.builder()
                .id(entity.getId())
                .milestoneId(entity.getMilestone().getId())
                .releaseId(entity.getRelease().getId())
                .associatedAt(entity.getAssociatedAt())
                .build();
    }
}
