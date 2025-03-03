package com.example.uiuxtools.repository;

import com.example.uiuxtools.model.FeatureItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FeatureItemRepository extends JpaRepository<FeatureItem, Integer> {
    // Custom query method to search for Feature Item by name
    List<FeatureItem> findByNameContaining(String name);

    // Custom query method to search for Feature Item by FeatureItemId
    List<FeatureItem> findByFeatureItemId(Integer FeatureItemId);

    // Custom query method to search for Feature Item by FeatureGroupId
    List<FeatureItem> findByFeatureGroupId(Integer FeatureGroupId);
}
