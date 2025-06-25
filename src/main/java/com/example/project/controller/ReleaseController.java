package com.example.project.controller;

import com.example.project.dto.ReleaseAssociationResponseDTO;
import com.example.project.service.AssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/releases")
public class ReleaseController {
    private final AssociationService associationService;

    @Autowired
    public ReleaseController(AssociationService associationService) {
        this.associationService = associationService;
    }

    @PostMapping("/{releaseId}/milestone/{milestoneId}")
    public ResponseEntity<ReleaseAssociationResponseDTO> associateReleaseWithMilestone(
            @PathVariable Long releaseId,
            @PathVariable Long milestoneId) {
        ReleaseAssociationResponseDTO response = associationService.associateReleaseWithMilestone(releaseId, milestoneId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}