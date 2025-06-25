package com.example.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "release")
@CompoundIndex(name = "unique_tag_per_project", def = "{'tag': 1, 'projectId': 1}", unique = true)
public class Release {
    @Id
    private String id;

    private String tag;

    private String description;

    private String projectId;

    private String milestoneId; // Nullable, one-to-one association
}
