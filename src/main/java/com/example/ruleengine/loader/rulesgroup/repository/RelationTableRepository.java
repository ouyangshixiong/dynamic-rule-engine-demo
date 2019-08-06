package com.example.ruleengine.loader.rulesgroup.repository;

import com.example.ruleengine.loader.rulesgroup.domain.RulesGroupRelationTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author alexouyang
 * @Date 2019-07-25
 */
@Repository
public interface RelationTableRepository extends JpaRepository<RulesGroupRelationTable,Long> {

    public Optional<List<RulesGroupRelationTable>> findAllByRulesGroupInfoId(Long rulesGroupInfoId);
}
