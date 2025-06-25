package com.example.milestonerelease.controller;

import com.example.milestonerelease.dto.MilestoneDTO;
import com.example.milestonerelease.service.MilestoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/milestones")
@RequiredArgsConstructor
public class MilestoneController {
    private final MilestoneService milestoneService;

    @PostMapping
    public ResponseEntity<MilestoneDTO> createMilestone(@Valid @RequestBody MilestoneDTO dto) {
        MilestoneDTO created = milestoneService.createMilestone(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MilestoneDTO> getMilestone(@PathVariable Long id) {
        return ResponseEntity.ok(milestoneService.getMilestone(id));
    }

    @GetMapping
    public ResponseEntity<List<MilestoneDTO>> getAllMilestones() {
        return ResponseEntity.ok(milestoneService.getAllMilestones());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MilestoneDTO> updateMilestone(@PathVariable Long id, @Valid @RequestBody MilestoneDTO dto) {
        return ResponseEntity.ok(milestoneService.updateMilestone(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMilestone(@PathVariable Long id) {
        milestoneService.deleteMilestone(id);
        return ResponseEntity.noContent().build();
    }
}
