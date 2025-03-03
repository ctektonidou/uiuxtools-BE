package com.example.uiuxtools.model;

import jakarta.persistence.*;

@Entity
@IdClass(RelationId.class) // Specify the composite key class
@Table(name = "Relation")
public class Relation {

    @Id
    @Column(name = "IdTool", nullable = false)
    private Integer idTool;

    @Id
    @Column(name = "IdFeatureItem", nullable = false)
    private Integer idFeatureItem;

    // Getters and Setters
    public Integer getIdTool() {
        return idTool;
    }

    public void setIdTool(Integer idTool) {
        this.idTool = idTool;
    }

    public Integer getIdFeatureItem() {
        return idFeatureItem;
    }

    public void setIdFeatureItem(Integer idFeatureItem) {
        this.idFeatureItem = idFeatureItem;
    }

    // Constructor
    public Relation() {}

    public Relation(Integer idTool, Integer idFeatureItem) {
        this.idTool = idTool;
        this.idFeatureItem = idFeatureItem;
    }
}
