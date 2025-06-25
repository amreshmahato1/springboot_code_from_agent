package com.example.milestonemodule.repository;

import com.example.milestonemodule.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    Optional<Milestone> findByName(String name);
}
