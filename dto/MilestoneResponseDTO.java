package com.example.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.Set;

@Schema(description = "Response DTO for Milestone details")
public class MilestoneResponseDTO {
    @Schema(description = "Milestone ID", example = "1")
    private Long id;

    @Schema(description = "Milestone name", example = "Release 1.0 Milestone")
    private String name;

    @Schema(description = "Milestone description", example = "Milestone for the first major release")
    private String description;

    @Schema(description = "Due date for the milestone", example = "2024-12-31")
    private LocalDate dueDate;

    @Schema(description = "Associated releases")
    private Set<ReleaseSummaryDTO> releases;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public Set<ReleaseSummaryDTO> getReleases() { return releases; }
    public void setReleases(Set<ReleaseSummaryDTO> releases) { this.releases = releases; }
}
