package com.example.project.repository;

import com.example.project.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    Optional<Milestone> findByTitleAndProjectId(String title, Long projectId);
    List<Milestone> findByProjectId(Long projectId);
    List<Milestone> findByTagAndProjectId(String tag, Long projectId);
}
