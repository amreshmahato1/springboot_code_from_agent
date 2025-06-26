package com.example.project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReleaseDto {
    private String id;
    @NotBlank
    private String tag;
    private String description;
    @NotBlank
    private String projectId;
    private String milestoneId;
}
