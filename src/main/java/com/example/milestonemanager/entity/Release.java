package com.example.milestonemanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "releases", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tag", "project_id"})
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String tag;

    @Size(max = 1024)
    private String description;

    @NotNull
    @Column(name = "project_id")
    private Long projectId;

    @OneToOne
    @JoinColumn(name = "milestone_id", unique = true)
    private Milestone milestone;
}