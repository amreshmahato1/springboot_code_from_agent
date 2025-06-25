package com.example.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "milestones", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "project_id"}),
        @UniqueConstraint(columnNames = {"title", "group_id"})
})
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @Column(length = 1024)
    private String description;

    @NotNull
    @Column(nullable = false)
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "milestone_releases",
            joinColumns = @JoinColumn(name = "milestone_id"),
            inverseJoinColumns = @JoinColumn(name = "release_id")
    )
    private Set<Release> releases = new HashSet<>();

    // Getters and Setters
    // ...
}