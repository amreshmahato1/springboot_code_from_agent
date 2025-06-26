package com.example.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for creating a Release.
 */
@Data
public class ReleaseCreateRequest {
    @NotBlank
    @Size(max = 255)
    private String tag;

    @Size(max = 1024)
    private String description;

    @NotNull
    private Long projectId;
}
