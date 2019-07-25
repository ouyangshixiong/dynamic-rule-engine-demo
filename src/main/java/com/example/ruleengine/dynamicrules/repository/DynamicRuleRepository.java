package com.example.ruleengine.dynamicrules.repository;

import com.example.ruleengine.dynamicrules.domain.DynamicRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author alexouyang
 * 2019-07-25
 */
@Repository
public interface DynamicRuleRepository extends JpaRepository<DynamicRule,Long> {

}
