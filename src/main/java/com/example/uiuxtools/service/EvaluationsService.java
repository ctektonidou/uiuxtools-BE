package com.example.uiuxtools.service;
import com.example.uiuxtools.model.Evaluations;
import com.example.uiuxtools.repository.EvaluationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationsService {
    private final EvaluationsRepository evaluationsRepository;

    // Constructor-based Dependency Injection
    public EvaluationsService(EvaluationsRepository evaluationsRepository) {
        this.evaluationsRepository = evaluationsRepository;
    }

    // Fetch all evaluations
    public List<Evaluations> getAllEvaluations() {
        return evaluationsRepository.findAll();
    }

    // Save or update an evaluation
    public Evaluations saveEvaluation(Evaluations evaluations) {
        return evaluationsRepository.save(evaluations);
    }

    // Search evaluations by user id
    public List<Evaluations> searchEvaluationsById(Integer userId) {
        return evaluationsRepository.findByUserId(userId);
    }

    // Search evaluations by tool id
    public List<Evaluations> searchEvaluationsByToolId(Integer toolId) {
        return evaluationsRepository.findByToolId(toolId);
    }
}
