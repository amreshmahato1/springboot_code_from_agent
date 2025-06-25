package com.example.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "releases")
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String tagName;

    @Column(length = 1024)
    private String description;

    @Column(nullable = false)
    private LocalDate releasedAt;

    @ManyToMany(mappedBy = "releases")
    private Set<Milestone> milestones = new HashSet<>();

    // Getters and Setters
    // ...
}