package com.example.project.service;

import com.example.project.dto.MilestoneCreateRequest;
import com.example.project.dto.MilestoneResponse;
import com.example.project.entity.Milestone;
import com.example.project.exception.DuplicateMilestoneTitleException;
import com.example.project.exception.MilestoneNotFoundException;
import com.example.project.repository.MilestoneRepository;
import com.example.project.util.ValidationUtil;
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
    public MilestoneResponse createMilestone(MilestoneCreateRequest request) {
        // Validate date range
        validationUtil.validateDateRange(request.getStartDate(), request.getDueDate());

        // Validate project/group association
        Milestone tempMilestone = new Milestone();
        tempMilestone.setProjectId(request.getProjectId());
        tempMilestone.setGroupId(request.getGroupId());
        validationUtil.validateMilestoneProjectOrGroup(tempMilestone);

        // Check for unique title within project or group
        if (request.getProjectId() != null) {
            milestoneRepository.findByTitleAndProjectId(request.getTitle(), request.getProjectId())
                .ifPresent(m -> { throw new DuplicateMilestoneTitleException("Milestone title must be unique within the project."); });
        } else if (request.getGroupId() != null) {
            milestoneRepository.findByTitleAndGroupId(request.getTitle(), request.getGroupId())
                .ifPresent(m -> { throw new DuplicateMilestoneTitleException("Milestone title must be unique within the group."); });
        }

        // Create and save milestone
        Milestone milestone = new Milestone();
        milestone.setTitle(request.getTitle());
        milestone.setDescription(request.getDescription());
        milestone.setStartDate(request.getStartDate());
        milestone.setDueDate(request.getDueDate());
        milestone.setState(request.getState());
        milestone.setProjectId(request.getProjectId());
        milestone.setGroupId(request.getGroupId());
        Milestone saved = milestoneRepository.save(milestone);
        return new MilestoneResponse(
            saved.getId(),
            saved.getTitle(),
            saved.getDescription(),
            saved.getStartDate(),
            saved.getDueDate(),
            saved.getState(),
            saved.getProjectId(),
            saved.getGroupId()
        );
    }

    public Milestone getMilestoneById(Long milestoneId) {
        return milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new MilestoneNotFoundException("Milestone not found: " + milestoneId));
    }
}
