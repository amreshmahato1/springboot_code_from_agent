package com.example.project.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReleaseDto {
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String version;

    @Size(max = 255)
    private String description;

    @NotNull
    private LocalDate releaseDate;

    private List<MilestoneDto> milestones;
}