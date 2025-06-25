package com.example.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class MilestoneRequestDTO {
    @NotBlank(message = "Title is mandatory")
    @Size(max = 255)
    private String title;

    @Size(max = 1024)
    private String description;

    @NotNull(message = "Start date is mandatory")
    private LocalDate startDate;

    @NotNull(message = "Due date is mandatory")
    private LocalDate dueDate;

    @NotBlank(message = "State is mandatory")
    private String state;

    private Long projectId;
    private Long groupId;

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    public Long getGroupId() { return groupId; }
    public void setGroupId(Long groupId) { this.groupId = groupId; }
}