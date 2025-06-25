package com.example.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Summary DTO for Release details in Milestone responses")
public class ReleaseSummaryDTO {
    @Schema(description = "Release ID", example = "1")
    private Long id;

    @Schema(description = "Release version", example = "1.0.0")
    private String version;

    @Schema(description = "Release date", example = "2024-12-01")
    private LocalDate releaseDate;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
}
