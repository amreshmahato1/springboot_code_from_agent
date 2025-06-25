package com.example.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "milestone")
@CompoundIndexes({
    @CompoundIndex(name = "unique_title_per_project", def = "{'title': 1, 'projectId': 1}", unique = true),
    @CompoundIndex(name = "unique_title_per_group", def = "{'title': 1, 'groupId': 1}", unique = true)
})
public class Milestone {
    @Id
    private String id;

    private String title;

    private String description;

    private LocalDate startDate;

    private LocalDate dueDate;

    private String state;

    private String projectId;

    private String groupId;
}
