package com.example.milestonerelease.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "milestone_release_associations", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"milestone_id", "release_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MilestoneReleaseAssociation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "milestone_id", nullable = false)
    private Milestone milestone;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "release_id", nullable = false)
    private Release release;

    @Column(nullable = false)
    private LocalDateTime associatedAt;
}
