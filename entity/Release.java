package com.example.project.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

/**
 * Release entity representing a software release in MongoDB.
 */
@Document(collection = "release")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Release {
    @Id
    private String id;

    @Indexed(unique = true)
    private String version;

    private String description;

    private LocalDate releaseDate;

    /**
     * Status of the release (e.g., PLANNED, RELEASED, ARCHIVED)
     */
    private String status;

    /**
     * List of milestone IDs associated with this release (for quick lookup)
     */
    private List<String> milestoneIds;

    // Add additional fields and validation as per LLD
}
