package com.example.ruleengine.loader.rulesgroup;

import com.example.ruleengine.loader.rulesgroup.domain.RulesGroupInfo;
import com.example.ruleengine.loader.rulesgroup.domain.DynamicRule;
import com.example.ruleengine.loader.rulesgroup.domain.RulesGroupRelationTable;
import com.example.ruleengine.loader.rulesgroup.repository.RulesGroupInfoRepository;
import com.example.ruleengine.loader.rulesgroup.repository.DynamicRuleRepository;
import com.example.ruleengine.loader.rulesgroup.repository.RelationTableRepository;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.YamlRuleDefinitionReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author alexouyang
 * @Date 2019-07-25
 */
@Component
public abstract class AbstractDbRulesGroupLoader implements DbRulesGroupLoader {

    private static final Logger log = LoggerFactory.getLogger(AbstractDbRulesGroupLoader.class);

    @Autowired
    private DynamicRuleRepository dynamicRuleRepository;

    @Autowired
    private RelationTableRepository relationTableRepository;

    @Autowired
    private RulesGroupInfoRepository rulesGroupInfoRepository;

    @Override
    public abstract Rules doLoad(Long rulesGroupInfoId);

    protected List<Rule> selectRulesFromDB(Long rulesGroupInfoId){
        List<Rule> rules = new ArrayList<>();

        Optional<RulesGroupInfo> rs = rulesGroupInfoRepository.findById(rulesGroupInfoId);
        if( rs.isPresent() ){
            RulesGroupInfo rulesGroupInfo = rs.get();
            log.info("success doLoad rulesGroupInfo =" + rulesGroupInfo.toString());
            Optional<List<RulesGroupRelationTable>> rs2 = relationTableRepository.findAllByRulesGroupInfoId(rulesGroupInfo.getId());
            if(rs2.isPresent()){
                List<RulesGroupRelationTable> rulesGroupRelationTableList = rs2.get();
                for( RulesGroupRelationTable rulesGroupRelationTable : rulesGroupRelationTableList){
                    Long dynamicRuleId = rulesGroupRelationTable.getDynamicRuleId();
                    Optional<DynamicRule> rs3 = dynamicRuleRepository.findById(dynamicRuleId);
                    if( rs3.isPresent() ){
                        DynamicRule dynamicRule = rs3.get();
                        MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
                        try {
                            Rule rule = ruleFactory.createRule(new StringReader(dynamicRule.getContent()));
                            rules.add(rule);
                        }catch (Exception e){
                            log.error("rule content 解析不出来, dynamicRuleId=" + dynamicRuleId);
                        }

                    }else{
                        log.error("找不到DynamicRule id=" + rs3);
                    }

                }
            }else{
                log.error("找不到对应的关系表记录 rulesGroupInfo=" + rulesGroupInfo.getId());
            }

        }else{
            log.error("找不到businessRuleDefinition id=" + rulesGroupInfoId);
        }

        return rules;
    }
}
