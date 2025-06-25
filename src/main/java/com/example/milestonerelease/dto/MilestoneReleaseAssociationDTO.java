package com.example.milestonerelease.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MilestoneReleaseAssociationDTO {
    private Long id;
    private Long milestoneId;
    private Long releaseId;
    private LocalDateTime associatedAt;
}
