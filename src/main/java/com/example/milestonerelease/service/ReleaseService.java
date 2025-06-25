package com.example.milestonerelease.service;

import com.example.milestonerelease.dto.ReleaseDTO;
import com.example.milestonerelease.entity.Release;
import com.example.milestonerelease.exception.DuplicateResourceException;
import com.example.milestonerelease.exception.ResourceNotFoundException;
import com.example.milestonerelease.repository.ReleaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReleaseService {
    private final ReleaseRepository releaseRepository;

    @Transactional
    public ReleaseDTO createRelease(ReleaseDTO dto) {
        if (releaseRepository.existsByVersion(dto.getVersion())) {
            throw new DuplicateResourceException("Release with version '" + dto.getVersion() + "' already exists.");
        }
        Release release = Release.builder()
                .version(dto.getVersion())
                .releaseDate(dto.getReleaseDate())
                .notes(dto.getNotes())
                .build();
        Release saved = releaseRepository.save(release);
        return toDTO(saved);
    }

    @Transactional(readOnly = true)
    public ReleaseDTO getRelease(Long id) {
        Release release = releaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Release not found with id: " + id));
        return toDTO(release);
    }

    @Transactional(readOnly = true)
    public List<ReleaseDTO> getAllReleases() {
        return releaseRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public ReleaseDTO updateRelease(Long id, ReleaseDTO dto) {
        Release release = releaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Release not found with id: " + id));
        release.setVersion(dto.getVersion());
        release.setReleaseDate(dto.getReleaseDate());
        release.setNotes(dto.getNotes());
        Release saved = releaseRepository.save(release);
        return toDTO(saved);
    }

    @Transactional
    public void deleteRelease(Long id) {
        if (!releaseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Release not found with id: " + id);
        }
        releaseRepository.deleteById(id);
    }

    public Release toEntity(ReleaseDTO dto) {
        return Release.builder()
                .id(dto.getId())
                .version(dto.getVersion())
                .releaseDate(dto.getReleaseDate())
                .notes(dto.getNotes())
                .build();
    }

    public ReleaseDTO toDTO(Release entity) {
        return ReleaseDTO.builder()
                .id(entity.getId())
                .version(entity.getVersion())
                .releaseDate(entity.getReleaseDate())
                .notes(entity.getNotes())
                .build();
    }
}
