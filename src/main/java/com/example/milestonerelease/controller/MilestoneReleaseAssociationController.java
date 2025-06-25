package com.example.milestonerelease.controller;

import com.example.milestonerelease.dto.MilestoneReleaseAssociationDTO;
import com.example.milestonerelease.service.MilestoneReleaseAssociationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/associations")
@RequiredArgsConstructor
public class MilestoneReleaseAssociationController {
    private final MilestoneReleaseAssociationService associationService;

    @PostMapping
    public ResponseEntity<MilestoneReleaseAssociationDTO> associate(@RequestParam Long milestoneId, @RequestParam Long releaseId) {
        MilestoneReleaseAssociationDTO created = associationService.associate(milestoneId, releaseId);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/milestone/{milestoneId}")
    public ResponseEntity<List<MilestoneReleaseAssociationDTO>> getAssociationsByMilestone(@PathVariable Long milestoneId) {
        return ResponseEntity.ok(associationService.getAssociationsByMilestone(milestoneId));
    }

    @GetMapping("/release/{releaseId}")
    public ResponseEntity<List<MilestoneReleaseAssociationDTO>> getAssociationsByRelease(@PathVariable Long releaseId) {
        return ResponseEntity.ok(associationService.getAssociationsByRelease(releaseId));
    }

    @DeleteMapping
    public ResponseEntity<Void> removeAssociation(@RequestParam Long milestoneId, @RequestParam Long releaseId) {
        associationService.removeAssociation(milestoneId, releaseId);
        return ResponseEntity.noContent().build();
    }
}
