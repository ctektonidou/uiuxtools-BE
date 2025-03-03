package com.example.uiuxtools.controller;

import com.example.uiuxtools.model.FeatureGroup;
import com.example.uiuxtools.service.FeatureGroupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/feature/group")
public class FeatureGroupController {
    private final FeatureGroupService featureGroupService;

    // Constructor-based Dependency Injection
    public FeatureGroupController(FeatureGroupService featureGroupService) {
        this.featureGroupService = featureGroupService;
    }

    // Get all feature groups
    @GetMapping
    public List<FeatureGroup> getAllFeatureGroups() {
        return featureGroupService.getAllFeatureGroups();
    }
}
