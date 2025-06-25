package com.example.project.service;

import com.example.project.dto.MilestoneRequestDTO;
import com.example.project.dto.MilestoneResponseDTO;
import com.example.project.entity.Milestone;
import com.example.project.exception.MilestoneNotFoundException;
import com.example.project.repository.MilestoneRepository;
import com.example.project.utility.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MilestoneService {
    private final MilestoneRepository milestoneRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public MilestoneService(MilestoneRepository milestoneRepository, ValidationUtil validationUtil) {
        this.milestoneRepository = milestoneRepository;
        this.validationUtil = validationUtil;
    }

    @Transactional
    public MilestoneResponseDTO createMilestone(MilestoneRequestDTO request) {
        // Unique title validation (project or group)
        if (request.getProjectId() != null) {
            validationUtil.validateMilestoneTitleUnique(
                milestoneRepository.findByTitleAndProjectId(request.getTitle(), request.getProjectId()),
                request.getTitle()
            );
        } else if (request.getGroupId() != null) {
            validationUtil.validateMilestoneTitleUnique(
                milestoneRepository.findByTitleAndGroupId(request.getTitle(), request.getGroupId()),
                request.getTitle()
            );
        }
        // Date range validation
        validationUtil.validateDateRange(request.getStartDate(), request.getDueDate());
        // Map DTO to entity
        Milestone milestone = new Milestone();
        milestone.setTitle(request.getTitle());
        milestone.setDescription(request.getDescription());
        milestone.setStartDate(request.getStartDate());
        milestone.setDueDate(request.getDueDate());
        milestone.setState(request.getState());
        milestone.setProjectId(request.getProjectId());
        milestone.setGroupId(request.getGroupId());
        Milestone saved = milestoneRepository.save(milestone);
        // Map entity to response DTO
        return mapToResponseDTO(saved);
    }

    public Milestone findMilestoneOrThrow(Long milestoneId) {
        return milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new MilestoneNotFoundException("Milestone not found with id: " + milestoneId));
    }

    private MilestoneResponseDTO mapToResponseDTO(Milestone milestone) {
        MilestoneResponseDTO dto = new MilestoneResponseDTO();
        dto.setId(milestone.getId());
        dto.setTitle(milestone.getTitle());
        dto.setDescription(milestone.getDescription());
        dto.setStartDate(milestone.getStartDate());
        dto.setDueDate(milestone.getDueDate());
        dto.setState(milestone.getState());
        dto.setProjectId(milestone.getProjectId());
        dto.setGroupId(milestone.getGroupId());
        return dto;
    }
}