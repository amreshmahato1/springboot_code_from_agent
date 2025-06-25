package com.example.project.service;

import com.example.project.entity.Release;
import com.example.project.exception.ReleaseNotFoundException;
import com.example.project.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReleaseService {
    private final ReleaseRepository releaseRepository;

    @Autowired
    public ReleaseService(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    public Release findReleaseOrThrow(Long releaseId) {
        return releaseRepository.findById(releaseId)
                .orElseThrow(() -> new ReleaseNotFoundException("Release not found with id: " + releaseId));
    }

    public Optional<Release> findByTagAndProjectId(String tag, Long projectId) {
        return releaseRepository.findByTagAndProjectId(tag, projectId);
    }
}