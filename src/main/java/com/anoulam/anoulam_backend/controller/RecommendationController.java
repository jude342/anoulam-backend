package com.anoulam.anoulam_backend.controller;

import com.anoulam.anoulam_backend.dto.RecipeRecommendationDto;
import com.anoulam.anoulam_backend.service.RecommendationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dishes/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping()
    public ResponseEntity<List<RecipeRecommendationDto>> recommend(@RequestBody List<String> ingredients) {
        List<RecipeRecommendationDto> result = recommendationService.recommend(ingredients);
        return ResponseEntity.ok(result);
    }
}