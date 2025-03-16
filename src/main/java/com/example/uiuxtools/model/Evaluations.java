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

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getToolId() {
        return toolId;
    }

    public void setToolId(Integer toolId) {
        this.toolId = toolId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getEasyToUse() {
        return easyToUse;
    }

    public void setEasyToUse(Integer easyToUse) {
        this.easyToUse = easyToUse;
    }

    public Integer getTrueToChars() {
        return trueToChars;
    }

    public void setTrueToChars(Integer trueToChars) {
        this.trueToChars = trueToChars;
    }

    public Integer getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(Integer totalRating) {
        this.totalRating = totalRating;
    }
}
