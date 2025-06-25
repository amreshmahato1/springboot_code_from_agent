package com.example.project.controller;

import com.example.project.dto.MilestoneCreateRequestDTO;
import com.example.project.dto.MilestoneResponseDTO;
import com.example.project.service.MilestoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/milestones")
@Tag(name = "Milestone APIs", description = "APIs for Milestone creation and release association")
public class MilestoneController {

    private final MilestoneService milestoneService;

    @Autowired
    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @Operation(summary = "Create a new milestone and associate releases")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Milestone created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MilestoneResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Validation error or bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Release not found", content = @Content)
    })
    @PostMapping
    public ResponseEntity<MilestoneResponseDTO> createMilestone(
            @Valid @RequestBody MilestoneCreateRequestDTO requestDTO) {
        MilestoneResponseDTO response = milestoneService.createMilestone(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
