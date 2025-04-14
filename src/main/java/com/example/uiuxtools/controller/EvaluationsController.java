package com.example.uiuxtools.controller;

import com.example.uiuxtools.model.Evaluations;
import com.example.uiuxtools.service.EvaluationsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Map<String, Object>> getAllEvaluationsByToolId(@RequestParam Integer toolId) {
        List<Evaluations> evaluations = evaluationsService.searchEvaluationsByToolId(toolId);
        List<Map<String, Object>> response = new ArrayList<>();

        for (Evaluations eval : evaluations) {
            Map<String, Object> evalData = new HashMap<>();
            evalData.put("evaluationId", eval.getEvaluationId());
            evalData.put("userId", eval.getUserId());
            evalData.put("toolId", eval.getToolId());
            evalData.put("comment", eval.getComment());
            evalData.put("easyToUse", eval.getEasyToUse());
            evalData.put("trueToChars", eval.getTrueToChars());
            evalData.put("totalRating", eval.getTotalRating());
            evalData.put("finalRating", eval.getFinalRating());

            // NEW: Fetch username from Users table
            String userName = evaluationsService.getUserNameById(eval.getUserId());
            evalData.put("userName", userName);

            response.add(evalData);
        }

        return response;
    }

    // Add a new evaluation
    @PostMapping
    public Evaluations addEvaluation(@RequestBody Evaluations evaluation) {
        return evaluationsService.saveEvaluation(evaluation);
    }

    @DeleteMapping("/{id}")
    public void deleteEvaluation(@PathVariable Integer id) {
        evaluationsService.deleteEvaluationById(id);
    }

    @PutMapping("/{id}")
    public Evaluations updateEvaluation(@PathVariable Integer id, @RequestBody Evaluations evaluation) {
        evaluation.setEvaluationId(id);
        return evaluationsService.saveEvaluation(evaluation);
    }

    @GetMapping("/user/{userId}")
    public List<Map<String, Object>> getEvaluationsByUserId(@PathVariable Integer userId) {
        List<Evaluations> evaluations = evaluationsService.searchEvaluationsByUserId(userId);
        List<Map<String, Object>> response = new ArrayList<>();

        for (Evaluations eval : evaluations) {
            Map<String, Object> evalData = new HashMap<>();
            evalData.put("evaluationId", eval.getEvaluationId());
            evalData.put("userId", eval.getUserId());
            evalData.put("toolId", eval.getToolId());
            evalData.put("comment", eval.getComment());
            evalData.put("easyToUse", eval.getEasyToUse());
            evalData.put("trueToChars", eval.getTrueToChars());
            evalData.put("totalRating", eval.getTotalRating());
            evalData.put("finalRating", eval.getFinalRating());

            // Optional: include tool name here for convenience
            evalData.put("toolName", evaluationsService.getToolNameById(eval.getToolId()));

            response.add(evalData);
        }

        return response;
    }
}
