package com.example.project.service;

import com.example.project.dto.AssociationResponseDto;
import com.example.project.dto.ReleaseResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssociationServiceImpl implements AssociationService {

    private final ReleaseService releaseService;

    @Autowired
    public AssociationServiceImpl(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @Override
    @Transactional
    public AssociationResponseDto linkReleaseToMilestone(Long releaseId, Long milestoneId) {
        ReleaseResponseDto releaseResponse = releaseService.associateWithMilestone(releaseId, milestoneId);
        AssociationResponseDto response = new AssociationResponseDto();
        response.setReleaseId(releaseResponse.getId());
        response.setMilestoneId(releaseResponse.getMilestoneId());
        response.setMessage("Release successfully associated with milestone");
        return response;
    }
}
