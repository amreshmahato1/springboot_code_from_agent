package com.example.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Schema(description = "Request DTO for creating a Milestone and associating Releases")
public class MilestoneCreateRequestDTO {
    @NotBlank
    @Size(max = 100)
    @Schema(description = "Unique name of the milestone", example = "Release 1.0 Milestone")
    private String name;

    @Size(max = 255)
    @Schema(description = "Description of the milestone", example = "Milestone for the first major release")
    private String description;

    @NotNull
    @FutureOrPresent
    @Schema(description = "Due date for the milestone", example = "2024-12-31")
    private LocalDate dueDate;

    @Schema(description = "Set of Release IDs to associate with the milestone")
    private Set<Long> releaseIds;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public Set<Long> getReleaseIds() { return releaseIds; }
    public void setReleaseIds(Set<Long> releaseIds) { this.releaseIds = releaseIds; }
}
