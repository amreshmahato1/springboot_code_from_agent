package com.example.project.service;

import com.example.project.dto.MilestoneRequestDTO;
import com.example.project.entity.Milestone;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.repository.MilestoneRepository;
import com.example.project.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MilestoneService {
    private final MilestoneRepository milestoneRepository;
    private final ValidationUtil validationUtil;

    @Transactional
    public Milestone createMilestone(MilestoneRequestDTO dto) {
        if (milestoneRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Milestone name already exists.");
        }
        validationUtil.validateDueDate(dto.getDueDate());
        Milestone milestone = Milestone.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .dueDate(dto.getDueDate())
                .build();
        return milestoneRepository.save(milestone);
    }

    @Transactional(readOnly = true)
    public Milestone getMilestone(Long id) {
        return milestoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found with id: " + id));
    }
}
