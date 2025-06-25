package com.example.project.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a Milestone in the system.
 * Each Milestone can be associated with one Release.
 */
@Entity
@Table(name = "milestone")
public class Milestone {

    /**
     * Primary key for Milestone.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the milestone (required).
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * Description of the milestone.
     */
    @Column(length = 500)
    private String description;

    /**
     * Planned due date for the milestone.
     */
    @Column(name = "due_date")
    private LocalDate dueDate;

    /**
     * Status of the milestone (e.g., PLANNED, IN_PROGRESS, COMPLETED).
     */
    @Column(nullable = false, length = 30)
    private String status;

    /**
     * Reference to the associated Release (many milestones to one release).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "release_id")
    private Release release;

    // Constructors, getters, setters, equals, hashCode

    public Milestone() {
    }

    public Milestone(String name, String description, LocalDate dueDate, String status, Release release) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.release = release;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Milestone that = (Milestone) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
