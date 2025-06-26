package com.example.project.controller;

import com.example.project.dto.ReleaseCreateRequest;
import com.example.project.dto.ReleaseResponse;
import com.example.project.service.AssociationService;
import com.example.project.service.ReleaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/releases")
@RequiredArgsConstructor
@Tag(name = "Release Management", description = "APIs for managing releases and associations")
public class ReleaseController {
    private final ReleaseService releaseService;
    private final AssociationService associationService;

    @Operation(summary = "Create a new release")
    @PostMapping
    public ResponseEntity<ReleaseResponse> createRelease(@Valid @RequestBody ReleaseCreateRequest request) {
        ReleaseResponse response = releaseService.createRelease(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Associate a release with a milestone")
    @PostMapping("/{releaseId}/milestone/{milestoneId}")
    public ResponseEntity<Void> associateReleaseWithMilestone(@PathVariable Long releaseId, @PathVariable Long milestoneId) {
        associationService.associateReleaseWithMilestone(releaseId, milestoneId);
        return ResponseEntity.noContent().build();
    }
}
