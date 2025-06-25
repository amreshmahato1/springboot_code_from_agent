package com.example.project.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReleaseRequestDTO {
    @NotBlank
    @Size(max = 100)
    private String version;

    @Size(max = 255)
    private String description;

    @NotNull
    @FutureOrPresent
    private LocalDate releaseDate;
}
