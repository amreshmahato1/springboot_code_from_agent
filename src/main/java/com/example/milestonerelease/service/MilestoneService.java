package com.example.milestonerelease.service;

import com.example.milestonerelease.dto.MilestoneDTO;
import com.example.milestonerelease.entity.Milestone;
import com.example.milestonerelease.exception.DuplicateResourceException;
import com.example.milestonerelease.exception.ResourceNotFoundException;
import com.example.milestonerelease.repository.MilestoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MilestoneService {
    private final MilestoneRepository milestoneRepository;

    @Transactional
    public MilestoneDTO createMilestone(MilestoneDTO dto) {
        if (milestoneRepository.existsByName(dto.getName())) {
            throw new DuplicateResourceException("Milestone with name '" + dto.getName() + "' already exists.");
        }
        Milestone milestone = Milestone.builder()
                .name(dto.getName())
                .dueDate(dto.getDueDate())
                .description(dto.getDescription())
                .released(false)
                .build();
        Milestone saved = milestoneRepository.save(milestone);
        return toDTO(saved);
    }

    @Transactional(readOnly = true)
    public MilestoneDTO getMilestone(Long id) {
        Milestone milestone = milestoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found with id: " + id));
        return toDTO(milestone);
    }

    @Transactional(readOnly = true)
    public List<MilestoneDTO> getAllMilestones() {
        return milestoneRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public MilestoneDTO updateMilestone(Long id, MilestoneDTO dto) {
        Milestone milestone = milestoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Milestone not found with id: " + id));
        milestone.setName(dto.getName());
        milestone.setDueDate(dto.getDueDate());
        milestone.setDescription(dto.getDescription());
        Milestone saved = milestoneRepository.save(milestone);
        return toDTO(saved);
    }

    @Transactional
    public void deleteMilestone(Long id) {
        if (!milestoneRepository.existsById(id)) {
            throw new ResourceNotFoundException("Milestone not found with id: " + id);
        }
        milestoneRepository.deleteById(id);
    }

    public Milestone toEntity(MilestoneDTO dto) {
        return Milestone.builder()
                .id(dto.getId())
                .name(dto.getName())
                .dueDate(dto.getDueDate())
                .description(dto.getDescription())
                .released(dto.isReleased())
                .build();
    }

    public MilestoneDTO toDTO(Milestone entity) {
        return MilestoneDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .dueDate(entity.getDueDate())
                .description(entity.getDescription())
                .released(entity.isReleased())
                .build();
    }
}
