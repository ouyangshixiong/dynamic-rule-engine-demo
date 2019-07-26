package com.example.ruleengine.dynamicrules;

import org.jeasy.rules.api.Rules;

/**
 * @author alexouyang
 * @Date 2019-07-25
 */
public interface DbRulesGroupLoader {

    Rules doLoad(Long businessRuleDefinitionId);
}
