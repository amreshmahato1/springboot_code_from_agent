package com.example.milestonemodule.controller;

import com.example.milestonemodule.dto.MilestoneRequestDTO;
import com.example.milestonemodule.dto.MilestoneResponseDTO;
import com.example.milestonemodule.service.MilestoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/milestones")
@Tag(name = "Milestone API", description = "APIs for managing milestones and their release associations")
public class MilestoneController {
    private final MilestoneService milestoneService;

    @Autowired
    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @Operation(summary = "Create a new milestone")
    @PostMapping
    public ResponseEntity<MilestoneResponseDTO> createMilestone(@Valid @RequestBody MilestoneRequestDTO requestDTO) {
        MilestoneResponseDTO response = milestoneService.createMilestone(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get milestone by ID")
    @GetMapping("/{id}")
    public ResponseEntity<MilestoneResponseDTO> getMilestoneById(@PathVariable Long id) {
        MilestoneResponseDTO response = milestoneService.getMilestoneById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all milestones")
    @GetMapping
    public ResponseEntity<List<MilestoneResponseDTO>> getAllMilestones() {
        List<MilestoneResponseDTO> milestones = milestoneService.getAllMilestones();
        return ResponseEntity.ok(milestones);
    }

    @Operation(summary = "Update milestone by ID")
    @PutMapping("/{id}")
    public ResponseEntity<MilestoneResponseDTO> updateMilestone(@PathVariable Long id, @Valid @RequestBody MilestoneRequestDTO requestDTO) {
        MilestoneResponseDTO response = milestoneService.updateMilestone(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete milestone by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMilestone(@PathVariable Long id) {
        milestoneService.deleteMilestone(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Associate releases with a milestone")
    @PostMapping("/{id}/releases")
    public ResponseEntity<MilestoneResponseDTO> associateReleases(@PathVariable Long id, @RequestBody List<Long> releaseIds) {
        MilestoneResponseDTO response = milestoneService.associateReleases(id, releaseIds);
        return ResponseEntity.ok(response);
    }
}
