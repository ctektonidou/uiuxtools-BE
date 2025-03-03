package com.example.uiuxtools.controller;

import com.example.uiuxtools.model.FeatureItem;
import com.example.uiuxtools.service.FeatureItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feature/item")
public class FeatureItemController {
    private final FeatureItemService featureItemService;

    // Constructor-based Dependency Injection
    public FeatureItemController(FeatureItemService featureItemService) {
        this.featureItemService = featureItemService;
    }

    // Get all feature items by feature group id
    @GetMapping
    public List<FeatureItem> getAllFeatureItemsByFeatureId(@RequestBody Integer featureGroupId) {
        return featureItemService.searchFeatureItemsByFeatureItemId(featureGroupId);
    }
}
