package com.example.milestonerelease.controller;

import com.example.milestonerelease.dto.ReleaseDTO;
import com.example.milestonerelease.service.ReleaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/releases")
@RequiredArgsConstructor
public class ReleaseController {
    private final ReleaseService releaseService;

    @PostMapping
    public ResponseEntity<ReleaseDTO> createRelease(@Valid @RequestBody ReleaseDTO dto) {
        ReleaseDTO created = releaseService.createRelease(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReleaseDTO> getRelease(@PathVariable Long id) {
        return ResponseEntity.ok(releaseService.getRelease(id));
    }

    @GetMapping
    public ResponseEntity<List<ReleaseDTO>> getAllReleases() {
        return ResponseEntity.ok(releaseService.getAllReleases());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReleaseDTO> updateRelease(@PathVariable Long id, @Valid @RequestBody ReleaseDTO dto) {
        return ResponseEntity.ok(releaseService.updateRelease(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRelease(@PathVariable Long id) {
        releaseService.deleteRelease(id);
        return ResponseEntity.noContent().build();
    }
}
