package com.example.ruleengine.loader.rulesgroup;

import com.example.ruleengine.rules.EnrollActivationRuleGroup;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author alexouyang
 * @Date 2019-07-25
 */
@Component
public class ActivationDbRulesGroupLoader extends AbstractDbRulesGroupLoader {

    @Override
    public Rules doLoad(Long rulesGroupInfoId) {
        //load rules from db--->ruleengine
        List<Rule> ruleList = selectRulesFromDB(rulesGroupInfoId);
        Rules rules = new Rules();
        EnrollActivationRuleGroup enrollActivationRuleGroup = new EnrollActivationRuleGroup();
        for(Rule rule : ruleList){
        enrollActivationRuleGroup.addRule(rule);
        }
        rules.register(enrollActivationRuleGroup);
        return rules;
    }
}
