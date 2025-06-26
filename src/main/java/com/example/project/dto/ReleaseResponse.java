package com.example.project.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO for Release response.
 */
@Data
@Builder
public class ReleaseResponse {
    private Long id;
    private String tag;
    private String description;
    private Long projectId;
    private Long milestoneId;
}
