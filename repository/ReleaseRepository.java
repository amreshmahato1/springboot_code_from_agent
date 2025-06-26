package com.example.project.repository;

import com.example.project.entity.Release;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

/**
 * Repository for Release entity.
 */
@Repository
public interface ReleaseRepository extends MongoRepository<Release, String> {
    Optional<Release> findByVersion(String version);
    List<Release> findByStatus(String status);
    // Add additional query methods as per LLD/business needs
}
