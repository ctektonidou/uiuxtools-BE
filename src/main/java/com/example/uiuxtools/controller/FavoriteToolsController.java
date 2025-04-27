package com.example.uiuxtools.controller;

import com.example.uiuxtools.model.FavoriteTool;
import com.example.uiuxtools.service.FavoriteToolsService;
import com.example.uiuxtools.service.ToolsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteToolsController {

    private final FavoriteToolsService favoriteToolsService;
    private final ToolsService toolsService;

    public FavoriteToolsController(FavoriteToolsService favoriteToolsService, ToolsService toolsService) {
        this.favoriteToolsService = favoriteToolsService;
        this.toolsService = toolsService;
    }

    // 1. Get all favorite tools for a user
    @GetMapping("/user/{userId}")
    public List<Map<String, Object>> getFavoritesByUser(@PathVariable Integer userId) {
        List<FavoriteTool> favorites = favoriteToolsService.getFavoritesByUserId(userId);

        List<Integer> toolIds = favorites.stream()
                .map(FavoriteTool::getToolId)
                .toList();

        return toolsService.getToolsWithFeatures(toolIds);
    }

    // 2. Check if a specific tool is in user's favorites
    @GetMapping("/check")
    public boolean isFavorite(@RequestParam Integer userId, @RequestParam Integer toolId) {
        return favoriteToolsService.existsByUserIdAndToolId(userId, toolId);
    }

    // 3. Add to favorites
    @PostMapping
    public ResponseEntity<FavoriteTool> addFavorite(@RequestBody FavoriteTool favoriteTool) {
        FavoriteTool created = favoriteToolsService.addFavorite(favoriteTool.getUserId(), favoriteTool.getToolId());
        return ResponseEntity.ok(created);
    }

    // 4. Remove from favorites
    @DeleteMapping
    public ResponseEntity<Void> removeFavorite(@RequestParam Integer userId, @RequestParam Integer toolId) {
        favoriteToolsService.removeFavorite(userId, toolId);
        return ResponseEntity.noContent().build();
    }
}