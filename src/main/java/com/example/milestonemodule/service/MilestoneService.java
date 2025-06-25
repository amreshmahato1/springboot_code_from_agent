package com.example.milestonemodule.service;

import com.example.milestonemodule.dto.MilestoneRequestDTO;
import com.example.milestonemodule.dto.MilestoneResponseDTO;

import java.util.List;

public interface MilestoneService {
    MilestoneResponseDTO createMilestone(MilestoneRequestDTO requestDTO);
    MilestoneResponseDTO getMilestoneById(Long id);
    List<MilestoneResponseDTO> getAllMilestones();
    MilestoneResponseDTO updateMilestone(Long id, MilestoneRequestDTO requestDTO);
    void deleteMilestone(Long id);
    MilestoneResponseDTO associateReleases(Long milestoneId, List<Long> releaseIds);
}
