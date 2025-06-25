package com.example.project.repository;

import com.example.project.entity.Release;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ReleaseRepository extends MongoRepository<Release, String> {
    // Find release by tag within a project
    Optional<Release> findByTagAndProjectId(String tag, String projectId);

    // Find all releases by project
    List<Release> findByProjectId(String projectId);
}
