package com.example.milestonemodule.service;

import com.example.milestonemodule.dto.MilestoneRequestDTO;
import com.example.milestonemodule.dto.MilestoneResponseDTO;
import com.example.milestonemodule.dto.ReleaseSummaryDTO;
import com.example.milestonemodule.entity.Milestone;
import com.example.milestonemodule.entity.Release;
import com.example.milestonemodule.exception.ResourceNotFoundException;
import com.example.milestonemodule.repository.MilestoneRepository;
import com.example.milestonemodule.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MilestoneServiceImpl implements MilestoneService {
    private final MilestoneRepository milestoneRepository;
    private final ReleaseRepository releaseRepository;

    @Autowired
    public MilestoneServiceImpl(MilestoneRepository milestoneRepository, ReleaseRepository releaseRepository) {
        this.milestoneRepository = milestoneRepository;
        this.releaseRepository = releaseRepository;
    }

    @Override
    @Transactional
    public MilestoneResponseDTO createMilestone(MilestoneRequestDTO requestDTO) {
        Milestone milestone = new Milestone();
        milestone.setName(requestDTO.getName());
        milestone.setDescription(requestDTO.getDescription());
        milestone.setDueDate(requestDTO.getDueDate());
        if (requestDTO.getReleaseIds() != null && !requestDTO.getReleaseIds().isEmpty()) {
            Set<Release> releases = new HashSet<>(releaseRepository.findAllById(requestDTO.getReleaseIds()));
            milestone.setReleases(releases);
        }
        Milestone saved = milestoneRepository.save(milestone);
        return mapToResponseDTO(saved);
    }

    @Override
    public MilestoneResponseDTO getMilestoneById(Long id) {
        Milestone milestone = milestoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found with id: " + id));
        return mapToResponseDTO(milestone);
    }

    @Override
    public List<MilestoneResponseDTO> getAllMilestones() {
        return milestoneRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MilestoneResponseDTO updateMilestone(Long id, MilestoneRequestDTO requestDTO) {
        Milestone milestone = milestoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found with id: " + id));
        milestone.setName(requestDTO.getName());
        milestone.setDescription(requestDTO.getDescription());
        milestone.setDueDate(requestDTO.getDueDate());
        if (requestDTO.getReleaseIds() != null) {
            Set<Release> releases = new HashSet<>(releaseRepository.findAllById(requestDTO.getReleaseIds()));
            milestone.setReleases(releases);
        }
        Milestone updated = milestoneRepository.save(milestone);
        return mapToResponseDTO(updated);
    }

    @Override
    @Transactional
    public void deleteMilestone(Long id) {
        Milestone milestone = milestoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found with id: " + id));
        milestoneRepository.delete(milestone);
    }

    @Override
    @Transactional
    public MilestoneResponseDTO associateReleases(Long milestoneId, List<Long> releaseIds) {
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found with id: " + milestoneId));
        Set<Release> releases = new HashSet<>(releaseRepository.findAllById(releaseIds));
        milestone.setReleases(releases);
        Milestone updated = milestoneRepository.save(milestone);
        return mapToResponseDTO(updated);
    }

    private MilestoneResponseDTO mapToResponseDTO(Milestone milestone) {
        MilestoneResponseDTO dto = new MilestoneResponseDTO();
        dto.setId(milestone.getId());
        dto.setName(milestone.getName());
        dto.setDescription(milestone.getDescription());
        dto.setDueDate(milestone.getDueDate());
        Set<ReleaseSummaryDTO> releaseDTOs = milestone.getReleases().stream().map(release -> {
            ReleaseSummaryDTO r = new ReleaseSummaryDTO();
            r.setId(release.getId());
            r.setVersion(release.getVersion());
            r.setDescription(release.getDescription());
            return r;
        }).collect(Collectors.toSet());
        dto.setReleases(releaseDTOs);
        return dto;
    }
}
