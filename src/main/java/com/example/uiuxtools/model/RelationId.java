package com.example.uiuxtools.model;

import java.io.Serializable;
import java.util.Objects;

public class RelationId implements Serializable {
    private Integer idTool;
    private Integer idFeatureItem;

    // Default constructor
    public RelationId() {}

    // Constructor
    public RelationId(Integer idTool, Integer idFeatureItem) {
        this.idTool = idTool;
        this.idFeatureItem = idFeatureItem;
    }

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

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationId that = (RelationId) o;
        return Objects.equals(idTool, that.idTool) && Objects.equals(idFeatureItem, that.idFeatureItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTool, idFeatureItem);
    }
}