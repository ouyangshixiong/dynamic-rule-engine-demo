package com.example.ruleengine.dynamicrules;

import com.example.ruleengine.rules.AgeRule;
import com.example.ruleengine.rules.EnrollUnitRuleGroup;
import com.example.ruleengine.rules.GenderRule;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author alexouyang
 * @Date 2019-07-25
 */
public class UnitRulesGroupLoader extends AbstractRulesGroupLoader {

    private static Logger log = LoggerFactory.getLogger(UnitRulesGroupLoader.class);

    @Override
    public void load() {
        List<Rule> ruleList = selectRulesFromDB();
        log.info("调试：看看有几个rule被加载了" + ruleList);
        Rules rules = new Rules();
        rules.register(new EnrollUnitRuleGroup(ruleList));
    }
}
