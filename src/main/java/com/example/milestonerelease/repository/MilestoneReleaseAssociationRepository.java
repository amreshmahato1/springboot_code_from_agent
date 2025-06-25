package com.example.milestonerelease.repository;

import com.example.milestonerelease.entity.MilestoneReleaseAssociation;
import com.example.milestonerelease.entity.Milestone;
import com.example.milestonerelease.entity.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface MilestoneReleaseAssociationRepository extends JpaRepository<MilestoneReleaseAssociation, Long> {
    Optional<MilestoneReleaseAssociation> findByMilestoneAndRelease(Milestone milestone, Release release);
    List<MilestoneReleaseAssociation> findByMilestone(Milestone milestone);
    List<MilestoneReleaseAssociation> findByRelease(Release release);
    boolean existsByMilestoneAndRelease(Milestone milestone, Release release);
}
