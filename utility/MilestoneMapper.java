package com.example.project.utility;

import com.example.project.dto.MilestoneResponseDTO;
import com.example.project.dto.ReleaseSummaryDTO;
import com.example.project.entity.Milestone;
import com.example.project.entity.Release;

import java.util.Set;
import java.util.stream.Collectors;

public class MilestoneMapper {
    public static MilestoneResponseDTO toResponseDTO(Milestone milestone) {
        MilestoneResponseDTO dto = new MilestoneResponseDTO();
        dto.setId(milestone.getId());
        dto.setName(milestone.getName());
        dto.setDescription(milestone.getDescription());
        dto.setDueDate(milestone.getDueDate());
        Set<ReleaseSummaryDTO> releases = milestone.getReleases().stream()
                .map(MilestoneMapper::toReleaseSummaryDTO)
                .collect(Collectors.toSet());
        dto.setReleases(releases);
        return dto;
    }

    public static ReleaseSummaryDTO toReleaseSummaryDTO(Release release) {
        ReleaseSummaryDTO dto = new ReleaseSummaryDTO();
        dto.setId(release.getId());
        dto.setVersion(release.getVersion());
        dto.setReleaseDate(release.getReleaseDate());
        return dto;
    }
}
