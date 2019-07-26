package com.example.ruleengine.dynamicrules;

import com.example.ruleengine.rules.EnrollUnitRuleGroup;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author alexouyang
 * @Date 2019-07-25
 */
@Component
public class UnitDbRulesGroupLoader extends AbstractDbRulesGroupLoader {

    private static Logger log = LoggerFactory.getLogger(UnitDbRulesGroupLoader.class);

    @Override
    public Rules doLoad(Long businessRuleDefinitionId) {
        //load rules from db--->ruleengine
        List<Rule> ruleList = selectRulesFromDB(businessRuleDefinitionId);
        log.info("调试：看看有几个rule被加载了" + ruleList);
        Rules rules = new Rules();
        EnrollUnitRuleGroup enrollUnitRuleGroup = new EnrollUnitRuleGroup();
        for(Rule rule : ruleList){
            enrollUnitRuleGroup.addRule(rule);
        }
        rules.register(enrollUnitRuleGroup);
        return rules;
    }
}
