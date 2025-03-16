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
        List<Evaluations> evaluations = evaluationsRepository.findAll();
        return evaluations;
    }

    // Save or update an evaluation
    public Evaluations saveEvaluation(Evaluations evaluation) {
        return evaluationsRepository.save(evaluation);
    }

    // Search evaluations by user id
    public List<Evaluations> searchEvaluationsByUserId(Integer userId) {
        return evaluationsRepository.findByUserId(userId);
    }

    // Search evaluations by tool id
    public List<Evaluations> searchEvaluationsByToolId(Integer toolId) {
        return evaluationsRepository.findByToolId(toolId);
    }
}
