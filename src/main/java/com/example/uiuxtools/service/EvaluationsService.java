package com.example.uiuxtools.service;

import com.example.uiuxtools.model.Evaluations;
import com.example.uiuxtools.repository.EvaluationsRepository;
import com.example.uiuxtools.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationsService {
    private final EvaluationsRepository evaluationsRepository;
    private final UsersRepository usersRepository;

    // Constructor-based Dependency Injection
    public EvaluationsService(EvaluationsRepository evaluationsRepository, UsersRepository usersRepository) {
        this.evaluationsRepository = evaluationsRepository;
        this.usersRepository = usersRepository;
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

    public double getFinalRatingByToolId(Integer toolId) {
        List<Evaluations> evaluations = evaluationsRepository.findByToolId(toolId);

        if (evaluations.isEmpty()) {
            return 0; // If no evaluations, return 0 rating
        }

        return evaluations.stream()
                .mapToDouble(e -> (e.getTotalRating() + e.getEasyToUse() + e.getTrueToChars()) / 3.0)
                .average()
                .orElse(0);
    }

    public long getReviewCountByToolId(Integer toolId) {
        return evaluationsRepository.countByToolId(toolId); // Fetch count of evaluations
    }

    public String getUserNameById(Integer userId) {
        return usersRepository.findById(userId)
                .map(user -> user.getFirstname() + " " + user.getLastname())
                .orElse("Unknown");
    }

}
