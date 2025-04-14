package com.example.uiuxtools.controller;

import com.example.uiuxtools.model.FavoriteTool;
import com.example.uiuxtools.service.FavoriteToolsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteToolsController {

    private final FavoriteToolsService favoriteToolsService;

    public FavoriteToolsController(FavoriteToolsService favoriteToolsService) {
        this.favoriteToolsService = favoriteToolsService;
    }

    // 1. Get all favorite tools for a user
    @GetMapping("/user/{userId}")
    public List<FavoriteTool> getFavoritesByUser(@PathVariable Integer userId) {
        return favoriteToolsService.getFavoritesByUserId(userId);
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