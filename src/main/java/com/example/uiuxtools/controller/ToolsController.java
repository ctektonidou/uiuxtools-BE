package com.example.uiuxtools.controller;

import com.example.uiuxtools.model.Tools;
import com.example.uiuxtools.service.EvaluationsService;
import com.example.uiuxtools.service.ToolsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
//Combines @Controller and @ResponseBody, indicating that the controller returns data (e.g., JSON) directly instead of rendering a view.
@RequestMapping("/api/tools")
//Sets the base URL for all endpoints in this controller, e.g., http://localhost:8080/api/tools
public class ToolsController {
    private final ToolsService toolsService;
    private final EvaluationsService evaluationsService;

    private static final Logger logger = LoggerFactory.getLogger(ToolsController.class);

    // Constructor-based Dependency Injection
    public ToolsController(ToolsService toolsService, EvaluationsService evaluationsService) {
        this.toolsService = toolsService;
        this.evaluationsService = evaluationsService;
    }

    // Get all tools
    @GetMapping
    public List<Tools> getAllTools() {
        return toolsService.getAllTools();
    }

    // Add a new tool
    @PostMapping
    public Tools addTool(@RequestBody Tools tools) {
        return toolsService.saveTool(tools);
    }

    // Delete a tool by ID
    @DeleteMapping("/{id}")
    public void deleteTool(@PathVariable("id") Integer toolId) {
        toolsService.deleteTool(toolId);
    }

    // Search tools by name
    @GetMapping("/search")
    public List<Tools> searchTools(@RequestParam String name) {
        return toolsService.searchTools(name);
    }

    // Get multiple tools by list of IDs
    @PostMapping("/batch")
    public List<Tools> getToolsByIds(@RequestBody List<Integer> toolIds) {
        return toolsService.getToolsByIds(toolIds);
    }

    @PostMapping("/compare")
    public List<Map<String, Object>> getToolComparison(@RequestBody List<Integer> toolIds) {
        return toolsService.getToolsWithFeatures(toolIds);
    }

    @PostMapping("/searchByFeatures")
    public List<Map<String, Object>> searchToolsByFeatureItems(@RequestBody(required = false) List<Integer> ids) {

        List<Tools> tools;

        if (ids == null || ids.isEmpty()) {
            // If no feature IDs are provided, return all tools
            tools = toolsService.getAllTools();
        } else {
            // Otherwise, filter tools based on feature items
            tools = toolsService.getToolsByFeatureItems(ids);
        }

        return tools.stream().map(tool -> {
            double finalRating = evaluationsService.getFinalRatingByToolId(tool.getToolId());
            long reviewCount = evaluationsService.getReviewCountByToolId(tool.getToolId());

            Map<String, Object> toolData = new HashMap<>();
            toolData.put("toolId", tool.getToolId());
            toolData.put("toolName", tool.getToolname());
            toolData.put("image", tool.getImage());
            toolData.put("finalRating", finalRating);
            toolData.put("reviewCount", reviewCount);

            return toolData;
        }).collect(Collectors.toList());
    }
}
