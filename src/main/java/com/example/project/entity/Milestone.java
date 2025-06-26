package com.example.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "milestone")
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

    @DBRef
    private List<Release> releases;
}
