package com.example.project.service;

import com.example.project.dto.AssociationResponseDto;

public interface AssociationService {
    AssociationResponseDto linkReleaseToMilestone(Long releaseId, Long milestoneId);
}
