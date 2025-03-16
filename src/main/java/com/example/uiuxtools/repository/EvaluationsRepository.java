package com.example.uiuxtools.repository;

import com.example.uiuxtools.model.Evaluations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationsRepository extends JpaRepository<Evaluations, Integer> {
    // Custom query method to search for tools by id
    List<Evaluations> findByUserId(Integer userId);

    // Custom query method to search for evaluations by evaluationId
    List<Evaluations> findByEvaluationId(Integer evaluationId);

    // Custom query method to search for evaluations by toolId
    List<Evaluations> findByToolId(Integer toolId);

    long countByToolId(Integer toolId);
}
