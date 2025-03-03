package com.example.uiuxtools.service;

import com.example.uiuxtools.model.Tools;
import com.example.uiuxtools.repository.ToolsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolsService {
    private final ToolsRepository toolsRepository;

    // Constructor-based Dependency Injection
    public ToolsService(ToolsRepository toolsRepository) {
        this.toolsRepository = toolsRepository;
    }

    // Fetch all tools
    public List<Tools> getAllTools() {
        return toolsRepository.findAll();
    }

    // Save or update a tool
    public Tools saveTool(Tools tools) {
        return toolsRepository.save(tools);
    }

    // Delete a tool by ID
    public void deleteTool(Integer toolId) {
        toolsRepository.deleteById(toolId);
    }

    // Search tools by name
    public List<Tools> searchTools(String toolname) {
        return toolsRepository.findByToolnameContaining(toolname);
    }

    // Search tools by id
    public List<Tools> searchTools(Integer toolId) {
        return toolsRepository.findByToolId(toolId);
    }

    // Fetch multiple tools by their IDs
    public List<Tools> getToolsByIds(List<Integer> toolIds) {
        return toolsRepository.findByToolIdIn(toolIds);
    }

}
