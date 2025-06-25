package com.example.project.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Document(collection = "release")
public class Release {
    @Id
    private String id;

    @NotBlank
    @Size(max = 50)
    private String tag;

    @Size(max = 500)
    private String description;

    @NotBlank
    private String projectId;

    @NotBlank
    private String milestoneId;

    // Getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getProjectId() {
        return projectId;
    }
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    public String getMilestoneId() {
        return milestoneId;
    }
    public void setMilestoneId(String milestoneId) {
        this.milestoneId = milestoneId;
    }
}
