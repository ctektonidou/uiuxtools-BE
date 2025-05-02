package com.example.uiuxtools.service;

import com.example.uiuxtools.model.FeatureGroup;
import com.example.uiuxtools.model.FeatureItem;
import com.example.uiuxtools.model.Relation;
import com.example.uiuxtools.model.Tools;
import com.example.uiuxtools.repository.FeatureGroupRepository;
import com.example.uiuxtools.repository.FeatureItemRepository;
import com.example.uiuxtools.repository.RelationRepository;
import com.example.uiuxtools.repository.ToolsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ToolsService {
    private final ToolsRepository toolsRepository;
    private final RelationRepository relationRepository;
    private final FeatureItemRepository featureItemRepository;
    private final FeatureGroupRepository featureGroupRepository;
    private final EvaluationsService evaluationsService;
    private final FavoriteToolsService favoriteToolsService;

    // Constructor-based Dependency Injection
    public ToolsService(
            ToolsRepository toolsRepository,
            RelationRepository relationRepository,
            FeatureItemRepository featureItemRepository,
            FeatureGroupRepository featureGroupRepository,
            EvaluationsService evaluationsService,
            FavoriteToolsService favoriteToolsService
    ) {
        this.toolsRepository = toolsRepository;
        this.relationRepository = relationRepository;
        this.featureItemRepository = featureItemRepository;
        this.featureGroupRepository = featureGroupRepository;
        this.evaluationsService = evaluationsService;
        this.favoriteToolsService = favoriteToolsService;
    }

    // Fetch all tools
    public List<Tools> getAllTools() {
        return toolsRepository.findAll();
    }

    // Delete a tool by ID
    @Transactional
    public void deleteTool(Integer toolId) {
        // Clean up favorites related to the tool
        favoriteToolsService.removeFavoritesByToolId(toolId);

        // Delete the tool itself
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
            toolDetails.put("description", tool.getDescription());
            toolDetails.put("link", tool.getLink());
            toolDetails.put("image", tool.getImage());

            double finalRating = evaluationsService.getFinalRatingByToolId(tool.getToolId());
            long reviewCount = evaluationsService.getReviewCountByToolId(tool.getToolId());
            toolDetails.put("finalRating", finalRating);
            toolDetails.put("reviewCount", reviewCount);

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

    public Tools saveTool(Tools tool) {
        return toolsRepository.save(tool);
    }

    // Method to save the image
    public String saveImage(MultipartFile file) throws IOException {
        String uploadDir = "uploads/";
        File uploadFolder = new File(uploadDir);

        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs(); // Create the uploads directory if it doesn't exist
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File destinationFile = new File(uploadDir + fileName);
        Files.copy(file.getInputStream(), Paths.get(destinationFile.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);

        return "/uploads/" + fileName; // Return URL for frontend
    }

    public void addToolFeatureRelation(Integer toolId, Integer featureItemId) {
        Relation relation = new Relation();
        relation.setIdTool(toolId);
        relation.setIdFeatureItem(featureItemId);
        relationRepository.save(relation);
    }

    public String saveBase64Image(String base64) {
        try {
            String uploadDir = "uploads/";
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs(); // Create uploads directory if it doesn't exist
            }

            // Extract Base64 data
            String base64Data = base64.split(",")[1]; // Remove "data:image/png;base64," prefix
            byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64Data);

            // Generate a unique filename
            String fileName = System.currentTimeMillis() + ".png";
            File imageFile = new File(uploadDir + fileName);
            Files.write(Paths.get(imageFile.getAbsolutePath()), decodedBytes);

            return "/uploads/" + fileName; // Return URL for frontend
        } catch (Exception e) {
            throw new RuntimeException("Error saving Base64 image", e);
        }
    }

    public Optional<Map<String, Object>> getToolEditData(Integer toolId) {
        Tools tool = toolsRepository.findById(toolId).orElse(null);
        if (tool == null) return Optional.empty();

        Map<String, Object> toolData = new HashMap<>();
        toolData.put("name", tool.getToolname());
        toolData.put("description", tool.getDescription());
        toolData.put("productLink", tool.getLink());
        toolData.put("imageUrl", tool.getImage());

        // Get selected feature item IDs
        List<Relation> relations = relationRepository.findByIdTool(toolId);
        List<Integer> featureItemIds = relations.stream()
                .map(Relation::getIdFeatureItem)
                .collect(Collectors.toList());

        toolData.put("featureItemIds", featureItemIds);
        return Optional.of(toolData);
    }

    public Tools getToolById(Integer toolId) {
        return toolsRepository.findById(toolId).orElse(null);
    }

    public List<Integer> getFeatureItemIdsByToolId(Integer toolId) {
        return relationRepository.findByIdTool(toolId)
                .stream()
                .map(Relation::getIdFeatureItem)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<Tools> updateTool(Integer toolId, Map<String, Object> toolData) {
        Tools tool = toolsRepository.findById(toolId).orElse(null);
        if (tool == null) return Optional.empty();

        tool.setToolname((String) toolData.get("name"));
        tool.setDescription((String) toolData.get("description"));
        tool.setLink((String) toolData.get("productLink"));

        // Handle optional image update
        String base64Image = (String) toolData.get("image");
        if (base64Image != null && !base64Image.isEmpty() && base64Image.startsWith("data:image")) {
            String imageUrl = saveBase64Image(base64Image);
            tool.setImage(imageUrl);
        }

        Tools updatedTool = toolsRepository.save(tool);

        // Update feature item relations
        List<Integer> newFeatureItemIds = (List<Integer>) toolData.get("featureItemIds");
        if (newFeatureItemIds != null) {
            relationRepository.deleteByToolId(toolId);
            for (Integer featureItemId : newFeatureItemIds) {
                addToolFeatureRelation(toolId, featureItemId);
            }
        }

        return Optional.of(updatedTool);
    }

}
