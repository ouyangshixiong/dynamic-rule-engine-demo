package com.example.ruleengine.loader.rules;

import org.jeasy.rules.api.Rule;
import org.springframework.stereotype.Component;

/**
 * @author alexouyang
 * @Date 2019-08-06
 */
@Component
public class SimpleDbRulesLoader extends AbstractDbRulesLoader {
    @Override
    public Rule doLoad(Long dynamicRuleId) {
        return selectRuleFromDB(dynamicRuleId);

    }
}
