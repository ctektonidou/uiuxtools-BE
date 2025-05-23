package com.example.uiuxtools.controller;

import com.example.uiuxtools.model.Tools;
import com.example.uiuxtools.service.EvaluationsService;
import com.example.uiuxtools.service.ToolsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*") // allow all origins
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

    // Create a new tool with image upload
    @PostMapping
    public ResponseEntity<Tools> addTool(@RequestBody Map<String, Object> toolData) {
        Tools tool = new Tools();
        tool.setToolname((String) toolData.get("name"));
        tool.setDescription((String) toolData.get("description"));
        tool.setLink((String) toolData.get("productLink"));

        // Handle Base64 Image
        String base64Image = (String) toolData.get("image");
        if (base64Image != null && !base64Image.isEmpty()) {
            String imageUrl = toolsService.saveBase64Image(base64Image);
            tool.setImage(imageUrl);
        }

        // Save tool and get the generated ID
        Tools savedTool = toolsService.saveTool(tool);

        // Parse and process feature item IDs
        List<Integer> featureItemIds = (List<Integer>) toolData.get("featureItemIds");
        if (featureItemIds != null) {
            for (Integer featureItemId : featureItemIds) {
                toolsService.addToolFeatureRelation(savedTool.getToolId(), featureItemId);
            }
        }

        return ResponseEntity.ok(savedTool);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<Map<String, Object>> getToolWithFeatures(@PathVariable("id") Integer toolId) {
        List<Map<String, Object>> toolList = toolsService.getToolsWithFeatures(List.of(toolId));

        if (toolList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(toolList.get(0)); // return only one tool
    }

    @GetMapping("/{id}/edit")
    public ResponseEntity<Map<String, Object>> getToolEditData(@PathVariable("id") Integer toolId) {
        Tools tool = toolsService.getToolById(toolId);
        if (tool == null) {
            return ResponseEntity.notFound().build();
        }

        List<Integer> featureItemIds = toolsService.getFeatureItemIdsByToolId(toolId);

        Map<String, Object> response = new HashMap<>();
        response.put("name", tool.getToolname());
        response.put("description", tool.getDescription());
        response.put("productLink", tool.getLink());
        response.put("image", tool.getImage());
        response.put("featureItemIds", featureItemIds);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tools> updateTool(@PathVariable("id") Integer toolId, @RequestBody Map<String, Object> toolData) {
        return toolsService.updateTool(toolId, toolData)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
