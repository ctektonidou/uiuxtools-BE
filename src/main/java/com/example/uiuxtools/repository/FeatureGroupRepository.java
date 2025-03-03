package com.example.uiuxtools.repository;

import com.example.uiuxtools.model.FeatureGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureGroupRepository extends JpaRepository<FeatureGroup, Integer> {

    // Custom query method to search for Feature Group by name
    List<FeatureGroup> findByNameContaining(String name);

    // Custom query method to search for Feature Group by id
    List<FeatureGroup> findByCustomId(Integer id);
}

