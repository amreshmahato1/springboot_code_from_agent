package com.example.project.repository;

import com.example.project.entity.Milestone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Milestone entity.
 */
@Repository
public interface MilestoneRepository extends MongoRepository<Milestone, String> {
    List<Milestone> findByReleaseId(String releaseId);
    boolean existsByNameAndReleaseId(String name, String releaseId);
    List<Milestone> findByStatus(String status);
    // Add additional query methods as per LLD/business needs
}
