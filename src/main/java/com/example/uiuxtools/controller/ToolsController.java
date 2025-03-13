package com.example.uiuxtools.controller;

import com.example.uiuxtools.model.Tools;
import com.example.uiuxtools.service.ToolsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
//Combines @Controller and @ResponseBody, indicating that the controller returns data (e.g., JSON) directly instead of rendering a view.
@RequestMapping("/api/tools")
//Sets the base URL for all endpoints in this controller, e.g., http://localhost:8080/api/tools
public class ToolsController {
    private final ToolsService toolsService;

    // Constructor-based Dependency Injection
    public ToolsController(ToolsService toolsService) {
        this.toolsService = toolsService;
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

}
