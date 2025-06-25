package com.example.project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "release")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String version;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @OneToMany(mappedBy = "release", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Milestone> milestones = new HashSet<>();

    // Additional fields and relationships as per LLD
}