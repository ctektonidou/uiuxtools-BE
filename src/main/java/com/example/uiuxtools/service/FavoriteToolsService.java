package com.example.uiuxtools.service;

import com.example.uiuxtools.model.FavoriteTool;
import com.example.uiuxtools.repository.FavoriteToolsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteToolsService {

    private final FavoriteToolsRepository favoriteToolsRepository;

    public FavoriteToolsService(FavoriteToolsRepository favoriteToolsRepository) {
        this.favoriteToolsRepository = favoriteToolsRepository;
    }

    // 1. Get all favorite tools for a user
    public List<FavoriteTool> getFavoritesByUserId(Integer userId) {
        return favoriteToolsRepository.findByUserId(userId);
    }

    // 2. Check if a tool is favorited
    public boolean existsByUserIdAndToolId(Integer userId, Integer toolId) {
        return favoriteToolsRepository.existsByUserIdAndToolId(userId, toolId);
    }

    // 3. Add to favorites
    public FavoriteTool addFavorite(Integer userId, Integer toolId) {
        if (!existsByUserIdAndToolId(userId, toolId)) {
            FavoriteTool favorite = new FavoriteTool();
            favorite.setUserId(userId);
            favorite.setToolId(toolId);
            return favoriteToolsRepository.save(favorite);
        }
        return null; // or throw an exception if needed
    }

    // 4. Remove from favorites
    @Transactional
    public void removeFavorite(Integer userId, Integer toolId) {
        favoriteToolsRepository.deleteByUserIdAndToolId(userId, toolId);
    }

    //5. Cleanup favorites when tool is deleted
    @Transactional
    public void removeFavoritesByToolId(Integer toolId) {
        favoriteToolsRepository.deleteByToolId(toolId);
    }
}