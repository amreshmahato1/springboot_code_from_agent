package com.example.milestonemodule.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

public class MilestoneRequestDTO {
    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 1024)
    private String description;

    @NotNull
    @FutureOrPresent
    private LocalDate dueDate;

    private Set<Long> releaseIds;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public Set<Long> getReleaseIds() { return releaseIds; }
    public void setReleaseIds(Set<Long> releaseIds) { this.releaseIds = releaseIds; }
}
