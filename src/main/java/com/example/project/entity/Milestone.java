package com.example.project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(
    name = "milestone",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title", "project_id", "group_id"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2048)
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private String state;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "group_id", nullable = false)
    private Long groupId;
}
