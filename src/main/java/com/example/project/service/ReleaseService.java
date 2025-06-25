package com.example.project.service;

import com.example.project.dto.ReleaseResponseDto;

public interface ReleaseService {
    ReleaseResponseDto associateWithMilestone(Long releaseId, Long milestoneId);
}
