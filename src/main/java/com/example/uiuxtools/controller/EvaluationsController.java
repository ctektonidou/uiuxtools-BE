package com.example.uiuxtools.controller;

import com.example.uiuxtools.model.Evaluations;
import com.example.uiuxtools.service.EvaluationsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluation")
public class EvaluationsController {
    private final EvaluationsService evaluationsService;

    // Constructor-based Dependency Injection
    public EvaluationsController(EvaluationsService evaluationsService) {
        this.evaluationsService = evaluationsService;
    }

    // Get all evaluations for a toolId
    @GetMapping
    public List<Evaluations> getAllEvaluationsByToolId(@RequestBody Integer toolId) {
        return evaluationsService.searchEvaluationsByToolId(toolId);
    }

    // Add a new evaluation
    @PostMapping
    public Evaluations addEvaluation(@RequestBody Evaluations evaluation) {
        return evaluationsService.saveEvaluation(evaluation);
    }
}
