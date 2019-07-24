package com.example.ruleengine.rules;

import org.jeasy.rules.support.ConditionalRuleGroup;

/**
 * @author alexouyang
 * @Date 2019-07-24
 */
public class EnrollConditionalRuleGroup extends ConditionalRuleGroup {
    public EnrollConditionalRuleGroup(Object... rules) {
        for(Object rule : rules ) {
            addRule(rule);
        }
    }
}
