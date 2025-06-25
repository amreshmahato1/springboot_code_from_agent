package com.example.milestonerelease.repository;

import com.example.milestonerelease.entity.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long> {
    Optional<Release> findByVersion(String version);
    boolean existsByVersion(String version);
}
