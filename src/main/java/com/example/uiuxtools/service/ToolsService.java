package com.example.uiuxtools.service;

import com.example.uiuxtools.model.FeatureGroup;
import com.example.uiuxtools.model.FeatureItem;
import com.example.uiuxtools.model.Relation;
import com.example.uiuxtools.model.Tools;
import com.example.uiuxtools.repository.FeatureGroupRepository;
import com.example.uiuxtools.repository.FeatureItemRepository;
import com.example.uiuxtools.repository.RelationRepository;
import com.example.uiuxtools.repository.ToolsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ToolsService {
    private final ToolsRepository toolsRepository;
    private final RelationRepository relationRepository;
    private final FeatureItemRepository featureItemRepository;
    private final FeatureGroupRepository featureGroupRepository;

    // Constructor-based Dependency Injection
    public ToolsService(ToolsRepository toolsRepository, RelationRepository relationRepository,
                        FeatureItemRepository featureItemRepository, FeatureGroupRepository featureGroupRepository) {
        this.toolsRepository = toolsRepository;
        this.relationRepository = relationRepository;
        this.featureItemRepository = featureItemRepository;
        this.featureGroupRepository = featureGroupRepository;
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

    public List<Map<String, Object>> getToolsWithFeatures(List<Integer> toolIds) {
        List<Tools> tools = toolsRepository.findByToolIdIn(toolIds);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Tools tool : tools) {
            Map<String, Object> toolDetails = new HashMap<>();
            toolDetails.put("toolId", tool.getToolId());
            toolDetails.put("toolName", tool.getToolname());

            List<Relation> relations = relationRepository.findByIdTool(tool.getToolId());
            List<Map<String, String>> features = new ArrayList<>();

            for (Relation relation : relations) {
                FeatureItem featureItem = featureItemRepository.findById(relation.getIdFeatureItem()).orElse(null);
                if (featureItem != null) {
                    FeatureGroup featureGroup = featureGroupRepository.findById(featureItem.getFeatureGroupId()).orElse(null);
                    if (featureGroup != null) {
                        Map<String, String> featureDetails = new HashMap<>();
                        featureDetails.put("group", featureGroup.getName());
                        featureDetails.put("feature", featureItem.getName());
                        features.add(featureDetails);
                    }
                }
            }
            toolDetails.put("features", features);
            result.add(toolDetails);
        }
        return result;
    }

    public List<Tools> getToolsByFeatureItems(List<Integer> featureItemIds) {
        List<Integer> toolIds = relationRepository.findToolIdsWithAllMatchingIds(featureItemIds, featureItemIds.size());
        return toolsRepository.findByToolIdIn(toolIds);
    }
}
