package com.example.uiuxtools.repository;

import com.example.uiuxtools.model.Tools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolsRepository extends JpaRepository<Tools, Integer> {
    // Custom query method to search for tools by name
    List<Tools> findByToolnameContaining(String toolname);

    // Custom query method to search for tools by id
    List<Tools> findByToolId(Integer toolId);

    //retrieves all rows from the Relation table where the id matches any value in the Integer[]
    List<Tools> findByToolIdIn(List<Integer> toolIds);
}
