package com.example.project.repository;

import com.example.project.entity.Milestone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface MilestoneRepository extends MongoRepository<Milestone, String> {
    // Find milestone by title within a project
    Optional<Milestone> findByTitleAndProjectId(String title, String projectId);

    // Find all milestones by project
    List<Milestone> findByProjectId(String projectId);

    // Find milestone by title within a group
    Optional<Milestone> findByTitleAndGroupId(String title, String groupId);

    // Find all milestones by group
    List<Milestone> findByGroupId(String groupId);
}
