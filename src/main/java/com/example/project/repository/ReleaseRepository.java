package com.example.project.repository;

import com.example.project.entity.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long> {
    Optional<Release> findByTagAndProjectId(String tag, Long projectId);
    Optional<Release> findByIdAndMilestoneId(Long id, Long milestoneId);
}
