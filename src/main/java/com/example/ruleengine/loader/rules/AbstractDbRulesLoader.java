package com.example.ruleengine.loader.rules;

import com.example.ruleengine.loader.rulesgroup.AbstractDbRulesGroupLoader;
import com.example.ruleengine.loader.rulesgroup.domain.DynamicRule;
import com.example.ruleengine.loader.rulesgroup.repository.DynamicRuleRepository;
import com.sun.deploy.security.ruleset.DefaultRule;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.YamlRuleDefinitionReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.StringReader;
import java.util.Optional;

/**
 * @author alexouyang
 * @Date 2019-08-05
 */
public abstract  class AbstractDbRulesLoader implements  DbRulesLoader{
    private static final Logger log = LoggerFactory.getLogger(AbstractDbRulesLoader.class);

    @Autowired
    private DynamicRuleRepository dynamicRuleRepository;

    @Override
    public abstract Rule doLoad(Long dynamicRuleId);

    /**
     * 注意会返回null
     * @param dynamicRuleId
     * @return
     */
    protected Rule selectRuleFromDB( Long dynamicRuleId ){
        Optional<DynamicRule> rs = dynamicRuleRepository.findById(dynamicRuleId);
        if( rs.isPresent() ){
            DynamicRule dynamicRule = rs.get();
            MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
            try {
                return ruleFactory.createRule(new StringReader(dynamicRule.getContent()));
            }catch (Exception e){
                log.error("rule content 解析不出来, dynamicRuleId=" + dynamicRuleId);
            }

        }else{
            log.error("找不到DynamicRule id=" + rs);
        }
        return null;
    }
}
