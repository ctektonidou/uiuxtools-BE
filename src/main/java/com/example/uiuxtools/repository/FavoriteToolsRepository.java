package com.example.uiuxtools.repository;

import com.example.uiuxtools.model.FavoriteTool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteToolsRepository extends JpaRepository<FavoriteTool, Integer> {
    // Delete all favorites for a specific tool (used when deleting the tool)
    void deleteByToolId(Integer toolId);

    // Get all favorites for a specific user
    List<FavoriteTool> findByUserId(Integer userId);

    // Check if a specific user has favorited a specific tool
    Optional<FavoriteTool> findByUserIdAndToolId(Integer userId, Integer toolId);

    // Delete a specific favorite entry
    void deleteByUserIdAndToolId(Integer userId, Integer toolId);

    boolean existsByUserIdAndToolId(Integer userId, Integer toolId);
}

