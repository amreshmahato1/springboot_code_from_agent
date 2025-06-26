package com.example.project.service;

import com.example.project.entity.Milestone;
import com.example.project.repository.MilestoneRepository;
import com.example.project.exception.ValidationException;
import com.example.project.util.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MilestoneService {
    private final MilestoneRepository milestoneRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public MilestoneService(MilestoneRepository milestoneRepository, ValidationUtil validationUtil) {
        this.milestoneRepository = milestoneRepository;
        this.validationUtil = validationUtil;
    }

    public Milestone createMilestone(Milestone milestone) {
        validateMilestone(milestone);
        return milestoneRepository.save(milestone);
    }

    public void validateMilestone(Milestone milestone) {
        if (!validationUtil.isValidMilestoneName(milestone.getName())) {
            throw new ValidationException("Invalid milestone name");
        }
        if (!validationUtil.isValidMilestoneDates(milestone.getStartDate(), milestone.getEndDate())) {
            throw new ValidationException("Milestone end date must be after start date");
        }
        // Add more validation as per LLD
    }
}
