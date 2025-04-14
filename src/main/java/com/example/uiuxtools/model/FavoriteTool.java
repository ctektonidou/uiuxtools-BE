package com.example.uiuxtools.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "favorite_tools", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "tool_id"})})
@Data
public class FavoriteTool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "tool_id", nullable = false)
    private Integer toolId;
}
