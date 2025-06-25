package com.example.milestonemodule.dto;

import java.time.LocalDate;
import java.util.Set;

public class MilestoneResponseDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDate dueDate;
    private Set<ReleaseSummaryDTO> releases;

    // Getters and setters
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
