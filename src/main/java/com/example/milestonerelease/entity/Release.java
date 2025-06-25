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
@Table(name = "releases")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String version;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime releaseDate;

    @Column(length = 1024)
    private String notes;
}
