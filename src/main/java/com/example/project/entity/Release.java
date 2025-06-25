package com.example.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "release",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tag", "project_id"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tag;

    @Column(length = 2048)
    private String description;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "milestone_id", referencedColumnName = "id", nullable = true)
    private Milestone milestone;
}
