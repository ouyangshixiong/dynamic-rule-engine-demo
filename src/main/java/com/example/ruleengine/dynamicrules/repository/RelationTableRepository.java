package com.example.ruleengine.dynamicrules.repository;

import com.example.ruleengine.dynamicrules.domain.RelationTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author alexouyang
 * @Date 2019-07-25
 */
@Repository
public interface RelationTableRepository extends JpaRepository<RelationTable,Long> {

    public Optional<List<RelationTable>> findAllByBusinessRuleDefinitionId(Long businessRuleDefinitionId);
}
