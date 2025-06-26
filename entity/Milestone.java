package com.example.project.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

/**
 * Milestone entity representing a project milestone in MongoDB.
 */
@Document(collection = "milestone")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@CompoundIndexes({
    @CompoundIndex(name = "unique_milestone_name_release", def = "{'name': 1, 'releaseId': 1}", unique = true)
})
public class Milestone {
    @Id
    private String id;

    @Indexed
    private String name;

    private String description;

    private LocalDate dueDate;

    /**
     * Reference to the associated Release (by release id)
     */
    @Indexed
    private String releaseId;

    /**
     * Status of the milestone (e.g., PLANNED, IN_PROGRESS, COMPLETED)
     */
    private String status;

    /**
     * List of tags or labels for the milestone
     */
    private List<String> tags;

    // Add additional fields and validation as per LLD
}
