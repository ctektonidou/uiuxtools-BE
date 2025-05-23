package com.example.uiuxtools.model;

import jakarta.persistence.*;

@Entity
@Table(name = "featureitem")
public class FeatureItem {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for primary key
    @Column(name = "FeatureItemId", nullable = false)
    private Integer featureItemId;

    @Column(name = "FeatureGroupId", nullable = false)
    private Integer featureGroupId;

    @Column(name = "Name", nullable = false, length = 255)
    private String name;

    // Getters and Setters

    public Integer getFeatureItemId() {
        return featureItemId;
    }

    public void setFeatureItemId(Integer featureItemId) {
        this.featureItemId = featureItemId;
    }

    public Integer getFeatureGroupId() {
        return featureGroupId;
    }

    public void setFeatureGroupId(Integer featureGroupId) {
        this.featureGroupId = featureGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Constructor
    public FeatureItem() {
    }

    public FeatureItem(Integer featureItemId, Integer featureGroupId, String name) {
        this.featureItemId = featureItemId;
        this.featureGroupId = featureGroupId;
        this.name = name;
    }
}
