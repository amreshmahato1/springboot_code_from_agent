package com.example.milestonemanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MilestoneCreateRequest {
    @Schema(description = "Milestone title", example = "Sprint 1")
    @NotBlank
    @Size(max = 255)
    private String title;

    @Schema(description = "Milestone description", example = "Initial sprint for Q1")
    @Size(max = 1024)
    private String description;

    @Schema(description = "Start date", example = "2024-07-01")
    @NotNull
    private LocalDate startDate;

    @Schema(description = "Due date", example = "2024-07-15")
    @NotNull
    private LocalDate dueDate;

    @Schema(description = "Project ID", example = "1001")
    @NotNull
    private Long projectId;
}
