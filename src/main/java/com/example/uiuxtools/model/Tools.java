package com.example.uiuxtools.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Tools")
public class Tools {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for primary key
    @Column(name = "ToolId", nullable = false)
    private Integer toolId;

    @Column(name = "Toolname", nullable = false, length = 255)
    private String toolname;

    @Column(name = "Description", nullable = false, length = 600)
    private String description;

    @Column(name = "Image", length = 1000)
    private String image;

    @Column(name = "Link", nullable = false, length = 1000)
    private String link;

    // Getters and Setters
    public Integer getToolId() {
        return toolId;
    }

    public void setToolId(Integer toolId) {
        this.toolId = toolId;
    }

    public String getToolname() {
        return toolname;
    }

    public void setToolname(String toolname) {
        this.toolname = toolname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    // Constructor
    public Tools() {
    }

    public Tools(Integer toolId, String toolname, String description, String image, String link) {
        this.toolId = toolId;
        this.toolname = toolname;
        this.description = description;
        this.image = image;
        this.link = link;
    }
}
