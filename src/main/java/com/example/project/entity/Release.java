package com.example.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "release")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String tag;

    @Column(length = 1000)
    private String description;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "milestone_id", nullable = false)
    private Milestone milestone;
}
