package com.example.uiuxtools.service;

import com.example.uiuxtools.model.Relation;
import com.example.uiuxtools.model.Tools;
import com.example.uiuxtools.repository.RelationRepository;
import com.example.uiuxtools.repository.ToolsRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RelationService {
    private final RelationRepository relationRepository;
    private final ToolsRepository toolsRepository;

    // Constructor-based Dependency Injection
    public RelationService(RelationRepository relationRepository, ToolsRepository toolsRepository) {
        this.relationRepository = relationRepository;
        this.toolsRepository = toolsRepository;
    }

    // Fetch all relations
    public List<Relation> getAllFeatureItems() {
        return relationRepository.findAll();
    }

    // Search feature relations by id
    public List<Relation> searchRelationsById(Integer idTool) {
        return relationRepository.findByIdTool(idTool);
    }

    // Αναζήτηση με κριτήρια, αφού κάθε κριτήριο έχει ένα idFeatureItem πρέπει το request της αβαζήτησης να είναι ένα ς πίνακας από αριθμούς. Με αυτούς τους αριθμούς ψάχνω σε όλο τον πίνακα
    //relations και φέρνω όλες τις γραμμές που έχουν αυτοά τα ids. από το αποτέλεσμα
    public List<Tools> getToolsByMatchingToolIds(Integer[] ids) {
        // Convert Integer[] to List<Integer>
        List<Integer> idList = Arrays.asList(ids);

        // Step 1: Find toolIds associated with all input ids
        List<Integer> matchingToolIds = relationRepository.findToolIdsWithAllMatchingIds(idList, idList.size());

        // Step 2: Fetch tools using the toolIds
        return toolsRepository.findByToolIdIn(matchingToolIds);
    }
}
