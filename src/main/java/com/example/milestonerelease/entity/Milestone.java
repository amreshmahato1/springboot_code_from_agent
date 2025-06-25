package com.example.milestonerelease.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "milestones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime dueDate;

    @Column(length = 1024)
    private String description;

    @Column(nullable = false)
    private boolean released = false;
}
