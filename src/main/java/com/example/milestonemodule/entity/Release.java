package com.example.milestonemodule.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "releases")
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String version;

    @Column(length = 1024)
    private String description;

    @ManyToMany(mappedBy = "releases", fetch = FetchType.LAZY)
    private Set<Milestone> milestones = new HashSet<>();

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Set<Milestone> getMilestones() { return milestones; }
    public void setMilestones(Set<Milestone> milestones) { this.milestones = milestones; }
}
