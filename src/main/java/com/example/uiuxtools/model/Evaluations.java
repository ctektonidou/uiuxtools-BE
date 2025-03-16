package com.example.uiuxtools.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Evaluations")
@Data // Lombok automatically generates Getters, Setters, toString(), etc.
@NoArgsConstructor
public class Evaluations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EvaluationId", nullable = false)
    private Integer evaluationId;

    @Column(name = "UserId", nullable = false)
    private Integer userId;

    @Column(name = "ToolId", nullable = false)
    private Integer toolId;

    @Column(name = "Comment", nullable = false, length = 2000)
    private String comment;

    @Column(name = "EasyToUse")
    private Integer easyToUse;

    @Column(name = "TrueToChars")
    private Integer trueToChars;

    @Column(name = "TotalRating")
    private Integer totalRating;

    @Transient // This field is **not stored in the database**; it's computed dynamically
    private Double finalRating;

    // Constructor including TotalRating
    public Evaluations(Integer evaluationId, Integer userId, String comment, Integer easyToUse, Integer trueToChars, Integer toolId, Integer totalRating) {
        this.evaluationId = evaluationId;
        this.userId = userId;
        this.comment = comment;
        this.easyToUse = easyToUse;
        this.trueToChars = trueToChars;
        this.toolId = toolId;
        this.totalRating = totalRating;
    }

    // **Compute final rating dynamically**
    public Double getFinalRating() {
        return (totalRating + easyToUse + trueToChars) / 3.0;
    }
}
