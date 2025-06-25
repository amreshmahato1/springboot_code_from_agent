package com.example.project.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a Release in the system.
 * Each Release can have multiple Milestones.
 */
@Entity
@Table(name = "release")
public class Release {

    /**
     * Primary key for Release.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the release (required).
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * Description of the release.
     */
    @Column(length = 500)
    private String description;

    /**
     * Planned release date.
     */
    @Column(name = "release_date")
    private LocalDate releaseDate;

    /**
     * Status of the release (e.g., UPCOMING, IN_PROGRESS, RELEASED).
     */
    @Column(nullable = false, length = 30)
    private String status;

    /**
     * Set of milestones associated with this release.
     */
    @OneToMany(mappedBy = "release", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Milestone> milestones = new HashSet<>();

    // Constructors, getters, setters, equals, hashCode

    public Release() {
    }

    public Release(String name, String description, LocalDate releaseDate, String status) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.status = status;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Milestone> getMilestones() {
        return milestones;
    }

    public void setMilestones(Set<Milestone> milestones) {
        this.milestones = milestones;
    }

    public void addMilestone(Milestone milestone) {
        milestones.add(milestone);
        milestone.setRelease(this);
    }

    public void removeMilestone(Milestone milestone) {
        milestones.remove(milestone);
        milestone.setRelease(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Release that = (Release) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
