package com.example.project.service;

import com.example.project.dto.ReleaseRequestDTO;
import com.example.project.entity.Milestone;
import com.example.project.entity.Release;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.repository.MilestoneRepository;
import com.example.project.repository.ReleaseRepository;
import com.example.project.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReleaseService {
    private final ReleaseRepository releaseRepository;
    private final MilestoneRepository milestoneRepository;
    private final ValidationUtil validationUtil;

    @Transactional
    public Release createRelease(ReleaseRequestDTO dto) {
        if (releaseRepository.existsByVersion(dto.getVersion())) {
            throw new IllegalArgumentException("Release version already exists.");
        }
        validationUtil.validateReleaseDate(dto.getReleaseDate());
        Release release = Release.builder()
                .version(dto.getVersion())
                .description(dto.getDescription())
                .releaseDate(dto.getReleaseDate())
                .build();
        return releaseRepository.save(release);
    }

    @Transactional(readOnly = true)
    public Release getRelease(Long id) {
        return releaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Release not found with id: " + id));
    }
}
