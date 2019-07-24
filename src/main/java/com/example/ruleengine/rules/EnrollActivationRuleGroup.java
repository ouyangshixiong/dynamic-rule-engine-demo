package com.example.ruleengine.rules;

import org.jeasy.rules.support.ActivationRuleGroup;

/**
 * @author alexouyang
 * @Date 2019-07-24
 */
public class EnrollActivationRuleGroup extends ActivationRuleGroup {
    public EnrollActivationRuleGroup(Object... rules) {
        for(Object rule : rules ) {
            addRule(rule);
        }
    }
}
