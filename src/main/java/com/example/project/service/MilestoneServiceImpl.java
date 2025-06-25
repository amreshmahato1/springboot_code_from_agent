package com.example.project.service;

import com.example.project.dto.MilestoneRequestDto;
import com.example.project.dto.MilestoneResponseDto;
import com.example.project.entity.Milestone;
import com.example.project.exception.DuplicateMilestoneTitleException;
import com.example.project.exception.InvalidDateRangeException;
import com.example.project.repository.MilestoneRepository;
import com.example.project.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MilestoneServiceImpl implements MilestoneService {

    private final MilestoneRepository milestoneRepository;

    @Autowired
    public MilestoneServiceImpl(MilestoneRepository milestoneRepository) {
        this.milestoneRepository = milestoneRepository;
    }

    @Override
    @Transactional
    public MilestoneResponseDto createMilestone(MilestoneRequestDto requestDto) {
        Optional<Milestone> existing = milestoneRepository.findByTitle(requestDto.getTitle());
        if (existing.isPresent()) {
            throw new DuplicateMilestoneTitleException("Milestone title must be unique");
        }
        if (!ValidationUtil.isValidDateRange(requestDto.getStartDate(), requestDto.getEndDate())) {
            throw new InvalidDateRangeException("Start date must be before end date");
        }
        Milestone milestone = new Milestone();
        milestone.setTitle(requestDto.getTitle());
        milestone.setStartDate(requestDto.getStartDate());
        milestone.setEndDate(requestDto.getEndDate());
        Milestone saved = milestoneRepository.save(milestone);
        MilestoneResponseDto response = new MilestoneResponseDto();
        response.setId(saved.getId());
        response.setTitle(saved.getTitle());
        response.setStartDate(saved.getStartDate());
        response.setEndDate(saved.getEndDate());
        return response;
    }
}
