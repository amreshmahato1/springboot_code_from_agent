package com.example.project.service;

import com.example.project.dto.MilestoneCreateRequestDTO;
import com.example.project.dto.MilestoneResponseDTO;

public interface MilestoneService {
    MilestoneResponseDTO createMilestone(MilestoneCreateRequestDTO requestDTO);
    // Additional methods such as getMilestoneById, listMilestones, etc. can be added as needed
}
