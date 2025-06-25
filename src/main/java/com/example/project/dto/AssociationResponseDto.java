package com.example.project.dto;

public class AssociationResponseDto {
    private Long releaseId;
    private Long milestoneId;
    private String message;

    // Getters and Setters
    public Long getReleaseId() { return releaseId; }
    public void setReleaseId(Long releaseId) { this.releaseId = releaseId; }

    public Long getMilestoneId() { return milestoneId; }
    public void setMilestoneId(Long milestoneId) { this.milestoneId = milestoneId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
