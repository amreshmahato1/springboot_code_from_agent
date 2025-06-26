package com.example.project.controller;

import com.example.project.dto.MilestoneCreateRequest;
import com.example.project.dto.MilestoneResponse;
import com.example.project.service.MilestoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/milestones")
@RequiredArgsConstructor
@Tag(name = "Milestone Management", description = "APIs for managing milestones")
public class MilestoneController {
    private final MilestoneService milestoneService;

    @Operation(summary = "Create a new milestone")
    @PostMapping
    public ResponseEntity<MilestoneResponse> createMilestone(@Valid @RequestBody MilestoneCreateRequest request) {
        MilestoneResponse response = milestoneService.createMilestone(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
