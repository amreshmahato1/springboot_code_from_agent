package com.example.project.controller;

import com.example.project.dto.MilestoneCreateRequest;
import com.example.project.dto.MilestoneResponse;
import com.example.project.service.MilestoneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/milestones")
public class MilestoneController {
    private final MilestoneService milestoneService;

    @Autowired
    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @PostMapping
    public ResponseEntity<MilestoneResponse> createMilestone(@Valid @RequestBody MilestoneCreateRequest request) {
        MilestoneResponse response = milestoneService.createMilestone(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
