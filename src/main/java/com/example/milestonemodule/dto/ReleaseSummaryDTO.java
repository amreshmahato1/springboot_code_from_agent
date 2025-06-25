package com.example.milestonemodule.dto;

public class ReleaseSummaryDTO {
    private Long id;
    private String version;
    private String description;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
