package com.example.project.repository;

import com.example.project.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    Optional<Milestone> findByTitleAndProjectId(String title, Long projectId);
    Optional<Milestone> findByTitleAndGroupId(String title, Long groupId);
}
