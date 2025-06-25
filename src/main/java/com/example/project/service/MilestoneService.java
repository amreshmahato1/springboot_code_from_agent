package com.example.project.service;

import com.example.project.dto.MilestoneRequestDto;
import com.example.project.dto.MilestoneResponseDto;

public interface MilestoneService {
    MilestoneResponseDto createMilestone(MilestoneRequestDto requestDto);
}
