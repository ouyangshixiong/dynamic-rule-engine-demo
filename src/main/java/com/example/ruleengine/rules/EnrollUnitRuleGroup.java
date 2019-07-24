package com.example.ruleengine.rules;

import org.jeasy.rules.support.UnitRuleGroup;

/**
 * @author alexouyang
 * 2019-07-24
 */
public class EnrollUnitRuleGroup extends UnitRuleGroup {
    public EnrollUnitRuleGroup(Object... rules) {
        for(Object rule : rules ) {
            addRule(rule);
        }
    }
}
