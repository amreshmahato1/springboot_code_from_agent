package com.example.project.controller;

import com.example.project.dto.MilestoneRequestDTO;
import com.example.project.entity.Milestone;
import com.example.project.service.MilestoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/milestones")
@RequiredArgsConstructor
@Tag(name = "Milestone API", description = "Endpoints for Milestone management")
public class MilestoneController {
    private final MilestoneService milestoneService;

    @PostMapping
    @Operation(summary = "Create a new milestone")
    public ResponseEntity<Milestone> createMilestone(@Valid @RequestBody MilestoneRequestDTO dto) {
        Milestone milestone = milestoneService.createMilestone(dto);
        return new ResponseEntity<>(milestone, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get milestone by ID")
    public ResponseEntity<Milestone> getMilestone(@PathVariable Long id) {
        Milestone milestone = milestoneService.getMilestone(id);
        return ResponseEntity.ok(milestone);
    }
}
