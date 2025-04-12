package com.example.uiuxtools.repository;

import com.example.uiuxtools.model.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RelationRepository extends JpaRepository<Relation, Integer> {
    // Custom query method to search for tools by IdFeatureItem
    List<Relation> findByIdFeatureItem(Integer idFeatureItem);

    // Custom query method to search for tools by id
    List<Relation> findByIdTool(Integer idTool);

    //retrieves all rows from the Relation table where the id matches any value in the Integer[].
    List<Relation> findByIdToolIn(List<Integer> ids);

    @Query("SELECT r.idTool FROM Relation r WHERE r.idFeatureItem IN :ids GROUP BY r.idTool HAVING COUNT(DISTINCT r.idFeatureItem) = :size")
        //eixe ena id edw pou den kserw ti itane
    List<Integer> findToolIdsWithAllMatchingIds(@Param("ids") List<Integer> ids, @Param("size") long size);

    //    r.id IN :ids: Filters rows where id matches any value in the input list.
//    GROUP BY r.toolId: Groups the rows by toolId.
//    HAVING COUNT(DISTINCT r.id) = :size: Ensures that each toolId is associated with all id values in the input list. The size parameter is the length of the input ids array.
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Relation r WHERE r.idTool = :toolId")
    void deleteByToolId(@Param("toolId") Integer toolId);

}
