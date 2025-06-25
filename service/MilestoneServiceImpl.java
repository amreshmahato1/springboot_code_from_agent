package com.example.project.service;

import com.example.project.dto.MilestoneCreateRequestDTO;
import com.example.project.dto.MilestoneResponseDTO;
import com.example.project.dto.ReleaseSummaryDTO;
import com.example.project.entity.Milestone;
import com.example.project.entity.Release;
import com.example.project.exception.BadRequestException;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.repository.MilestoneRepository;
import com.example.project.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
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
    public MilestoneResponseDTO createMilestone(MilestoneCreateRequestDTO requestDTO) {
        if (milestoneRepository.existsByName(requestDTO.getName())) {
            throw new BadRequestException("Milestone name already exists: " + requestDTO.getName());
        }

        Set<Release> releases = new HashSet<>();
        if (requestDTO.getReleaseIds() != null && !requestDTO.getReleaseIds().isEmpty()) {
            for (Long releaseId : requestDTO.getReleaseIds()) {
                Release release = releaseRepository.findById(releaseId)
                        .orElseThrow(() -> new ResourceNotFoundException("Release not found with id: " + releaseId));
                releases.add(release);
            }
        }

        Milestone milestone = new Milestone();
        milestone.setName(requestDTO.getName());
        milestone.setDescription(requestDTO.getDescription());
        milestone.setDueDate(requestDTO.getDueDate());
        milestone.setReleases(releases);

        Milestone saved = milestoneRepository.save(milestone);

        MilestoneResponseDTO response = new MilestoneResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setDescription(saved.getDescription());
        response.setDueDate(saved.getDueDate());
        response.setReleases(
                saved.getReleases().stream().map(this::toReleaseSummaryDTO).collect(Collectors.toSet())
        );
        return response;
    }

    private ReleaseSummaryDTO toReleaseSummaryDTO(Release release) {
        ReleaseSummaryDTO dto = new ReleaseSummaryDTO();
        dto.setId(release.getId());
        dto.setVersion(release.getVersion());
        dto.setReleaseDate(release.getReleaseDate());
        return dto;
    }
}
