package com.example.uiuxtools.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Evaluations")
public class Evaluations {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for primary key
    @Column(name = "ΕvaluationId", nullable = false)
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

    // Getters and Setters
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

    public Integer getToolId() {
        return toolId;
    }

    public void setToolId(Integer toolId) {
        this.toolId = toolId;
    }

    //Constructor

    public Evaluations() {
    }

    public Evaluations(Integer evaluationId, Integer userId, String comment, Integer easyToUse, Integer trueToChars, Integer toolId) {
        this.evaluationId = evaluationId;
        this.userId = userId;
        this.comment = comment;
        this.easyToUse = easyToUse;
        this.trueToChars = trueToChars;
        this.toolId = toolId;
    }
}
