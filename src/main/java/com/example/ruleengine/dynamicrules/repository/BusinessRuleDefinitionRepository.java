package com.example.ruleengine.dynamicrules.repository;

import com.example.ruleengine.dynamicrules.domain.BusinessRuleDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author alexouyang
 * @Date 2019-07-25
 */
@Repository
public interface BusinessRuleDefinitionRepository extends JpaRepository<BusinessRuleDefinition,Long> {
}
