package com.example.project.controller;

import com.example.project.dto.AssociationRequestDTO;
import com.example.project.entity.Release;
import com.example.project.service.AssociationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/associations")
@RequiredArgsConstructor
@Tag(name = "Association API", description = "Endpoints for associating releases with milestones")
public class AssociationController {
    private final AssociationService associationService;

    @PostMapping
    @Operation(summary = "Associate a release with a milestone")
    public ResponseEntity<Release> associateReleaseToMilestone(@Valid @RequestBody AssociationRequestDTO dto) {
        Release updatedRelease = associationService.associateReleaseToMilestone(dto);
        return ResponseEntity.ok(updatedRelease);
    }
}
