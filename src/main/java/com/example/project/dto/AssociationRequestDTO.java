package com.example.project.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssociationRequestDTO {
    @NotNull
    private Long releaseId;

    @NotNull
    private Long milestoneId;
}
