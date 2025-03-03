package com.example.uiuxtools.service;
import com.example.uiuxtools.model.FeatureGroup;
import com.example.uiuxtools.repository.FeatureGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureGroupService {
    private final FeatureGroupRepository featureGroupRepository;

    // Constructor-based Dependency Injection
    public FeatureGroupService(FeatureGroupRepository featureGroupRepository) {
        this.featureGroupRepository = featureGroupRepository;
    }

    // Fetch all feature groups
    public List<FeatureGroup> getAllFeatureGroups() {
        return featureGroupRepository.findAll();
    }

    // Search feature groups by id
    public List<FeatureGroup> searchFeatureGroupsById(Integer id) {
        return featureGroupRepository.findByCustomId(id);
    }

    // Search feature groups by name
    public List<FeatureGroup> searchFeatureGroupsByName(String name) {
        return featureGroupRepository.findByNameContaining(name);
    }
}
