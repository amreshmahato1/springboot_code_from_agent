package com.example.project.service;

import com.example.project.entity.Release;
import com.example.project.exception.ReleaseNotFoundException;
import com.example.project.exception.ReleaseTagNotUniqueException;
import com.example.project.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReleaseService {
    private final ReleaseRepository releaseRepository;

    @Autowired
    public ReleaseService(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    @Transactional(readOnly = true)
    public Release getReleaseById(Long releaseId) {
        return releaseRepository.findById(releaseId)
                .orElseThrow(() -> new ReleaseNotFoundException("Release not found: " + releaseId));
    }

    @Transactional(readOnly = true)
    public void validateUniqueTagWithinProject(String tag, Long projectId) {
        releaseRepository.findByTagAndProjectId(tag, projectId)
                .ifPresent(r -> { throw new ReleaseTagNotUniqueException("Release tag must be unique within the project."); });
    }

    @Transactional
    public Release saveRelease(Release release) {
        return releaseRepository.save(release);
    }
}
