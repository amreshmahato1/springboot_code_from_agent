package com.example.project.controller;

import com.example.project.dto.ReleaseRequestDTO;
import com.example.project.entity.Release;
import com.example.project.service.ReleaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/releases")
@RequiredArgsConstructor
@Tag(name = "Release API", description = "Endpoints for Release management")
public class ReleaseController {
    private final ReleaseService releaseService;

    @PostMapping
    @Operation(summary = "Create a new release")
    public ResponseEntity<Release> createRelease(@Valid @RequestBody ReleaseRequestDTO dto) {
        Release release = releaseService.createRelease(dto);
        return new ResponseEntity<>(release, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get release by ID")
    public ResponseEntity<Release> getRelease(@PathVariable Long id) {
        Release release = releaseService.getRelease(id);
        return ResponseEntity.ok(release);
    }
}
