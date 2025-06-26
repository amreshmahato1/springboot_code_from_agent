package com.example.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "releases", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tag", "project_id"})
})
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tag;

    @Column(length = 1024)
    private String description;

    @Column(nullable = false)
    private Long projectId;

    @Column
    private Long milestoneId;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    public Long getMilestoneId() { return milestoneId; }
    public void setMilestoneId(Long milestoneId) { this.milestoneId = milestoneId; }
}
