package com.example.uiuxtools.model;

import jakarta.persistence.*;

@Entity
@Table(name = "featuregroup")
public class FeatureGroup {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for primary key
    @Column(name = "Id", nullable = false)
    private Integer customId;

    @Column(name = "Name", nullable = false, length = 255)
    private String name;

    // Getters and Setters

    public Integer getId() {
        return customId;
    }

    public void setId(Integer id) {
        this.customId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Constructor

    public FeatureGroup() {
    }

    public FeatureGroup(Integer id, String name) {
        this.customId = id;
        this.name = name;
    }
}
