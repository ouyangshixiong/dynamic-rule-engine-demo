package com.example.ruleengine.loader.rules;

import org.jeasy.rules.api.Rule;

/**
 * @author alexouyang
 * @Date 2019-08-05
 */
public interface DbRulesLoader {

    Rule doLoad(Long dynamicRuleId);
}
