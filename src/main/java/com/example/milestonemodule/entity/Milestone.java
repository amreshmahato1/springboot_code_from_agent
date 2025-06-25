package com.example.milestonemodule.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "milestones")
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 1024)
    private String description;

    @Column(nullable = false)
    private LocalDate dueDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "milestone_releases",
        joinColumns = @JoinColumn(name = "milestone_id"),
        inverseJoinColumns = @JoinColumn(name = "release_id")
    )
    private Set<Release> releases = new HashSet<>();

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public Set<Release> getReleases() { return releases; }
    public void setReleases(Set<Release> releases) { this.releases = releases; }
}
