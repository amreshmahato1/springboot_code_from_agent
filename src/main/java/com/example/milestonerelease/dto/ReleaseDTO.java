package com.example.milestonerelease.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReleaseDTO {
    private Long id;

    @NotBlank
    private String version;

    @NotNull
    private LocalDateTime releaseDate;

    private String notes;
}
