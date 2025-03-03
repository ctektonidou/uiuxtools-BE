package com.example.uiuxtools.controller;

import com.example.uiuxtools.model.Relation;
import com.example.uiuxtools.model.Tools;
import com.example.uiuxtools.service.RelationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relation")
public class RelationController {
    private final RelationService relationService;

    // Constructor-based Dependency Injection
    public RelationController(RelationService relationService) {
        this.relationService = relationService;
    }

    // Get all feature items by tool id
    @GetMapping
    public List<Relation> getAllFeatureItemsByToolId(@RequestBody Integer toolId) {
        return relationService.searchRelationsById(toolId);
    }

    @GetMapping("/tools")
    public List<Tools> getToolsByRelationIds(@RequestParam Integer[] ids) {
        return relationService.getToolsByMatchingToolIds(ids);
    }
}
