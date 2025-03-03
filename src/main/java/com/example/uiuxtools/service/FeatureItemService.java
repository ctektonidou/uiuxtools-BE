package com.example.uiuxtools.service;
import com.example.uiuxtools.model.FeatureItem;
import com.example.uiuxtools.repository.FeatureItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureItemService {
    private final FeatureItemRepository featureItemRepository;

    // Constructor-based Dependency Injection
    public FeatureItemService(FeatureItemRepository featureItemRepository) {
        this.featureItemRepository = featureItemRepository;
    }

    // Fetch all feature items
    public List<FeatureItem> getAllFeatureItems() {
        return featureItemRepository.findAll();
    }

    // Search feature items by FeatureItemId
    public List<FeatureItem> searchFeatureItemsByFeatureItemId(Integer featureItemId) {
        return featureItemRepository.findByFeatureItemId(featureItemId);
    }

    // Search feature items by name
    public List<FeatureItem> searchFeatureItemsByName(String name) {
        return featureItemRepository.findByNameContaining(name);
    }

    // Search feature items by FeatureGroupId
    public List<FeatureItem> searchFeatureItemsByFeatureGroupId(Integer featureGroupId) {
        return featureItemRepository.findByFeatureGroupId(featureGroupId);
    }
}
